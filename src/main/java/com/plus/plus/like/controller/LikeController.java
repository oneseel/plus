package com.plus.plus.like.controller;

import com.plus.plus.common.CommonResponseDto;
import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.like.entity.Like;
import com.plus.plus.like.service.LikeService;
import com.plus.plus.user.UserDetailsImpl;
import com.plus.plus.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/like")
public class LikeController {

  private final LikeService likeService;

  // 게시글 좋아요
  @PostMapping
  public ResponseEntity<?> addLike(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PathVariable Long postId) {

    User loginUser = userDetails.getUser();

    try {
      Like like = likeService.addLike(loginUser, postId);
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(new CommonResponseDto("게시글 좋아요", HttpStatus.OK.value()));
    } catch (BusinessException be) {
      return ResponseEntity.status(be.getStatus())
          .body(new CommonResponseDto(be.getMessage(), be.getStatus()));
    }
  }

  // 게시글 좋아요 취소

}
