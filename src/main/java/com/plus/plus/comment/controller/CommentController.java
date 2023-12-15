package com.plus.plus.comment.controller;

import com.plus.plus.comment.dto.CommentRequestDto;
import com.plus.plus.comment.dto.CommentResponseDto;
import com.plus.plus.comment.service.CommentService;
import com.plus.plus.common.CommonResponseDto;
import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.user.UserDetailsImpl;
import com.plus.plus.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

  private final CommentService commentService;

  @PostMapping
  public ResponseEntity<?> createComment(
      @Valid @RequestBody CommentRequestDto requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PathVariable Long postId) {

    User loginuser = userDetails.getUser();

    try {
      CommentResponseDto responseDto = commentService.createComment(loginuser, requestDto, postId);
      return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    } catch (BusinessException be) {
      return ResponseEntity.status(be.getStatus())
          .body(new CommonResponseDto(be.getMessage(), be.getStatus()));
    }
  }

}
