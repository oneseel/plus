package com.plus.plus.post.controller;

import com.plus.plus.common.CommonResponseDto;
import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.post.dto.PostRequestDto;
import com.plus.plus.post.dto.PostResponseDto;
import com.plus.plus.post.service.PostService;
import com.plus.plus.user.UserDetailsImpl;
import com.plus.plus.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

  private final PostService postService;

  // 게시글 작성
  @PostMapping
  public ResponseEntity<PostResponseDto> createPost(
      @Valid @RequestBody PostRequestDto requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    User loginUser = userDetails.getUser();
    PostResponseDto responseDto = postService.createPost(loginUser, requestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  // 게시글 단건 조회
  @GetMapping("{postId}")
  public ResponseEntity<?> getPost(@PathVariable Long postId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    try {
      PostResponseDto responseDto = postService.getUser(postId);
      return ResponseEntity.ok().body(responseDto);
    } catch (BusinessException be) {
      return ResponseEntity.status(be.getStatus())
          .body(new CommonResponseDto(be.getMessage(), be.getStatus()));
    }
  }


  // 게시글 전체 조회

  // 게시글 수정

  // 게시글 삭제
}
