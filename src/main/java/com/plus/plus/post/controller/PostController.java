package com.plus.plus.post.controller;

import com.plus.plus.common.CommonResponseDto;
import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.post.dto.PostRequestDto;
import com.plus.plus.post.dto.PostResponseDto;
import com.plus.plus.post.dto.PostUpdateRequestDto;
import com.plus.plus.post.service.PostService;
import com.plus.plus.user.UserDetailsImpl;
import com.plus.plus.user.entity.User;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public ResponseEntity<?> getPost(@PathVariable Long postId) {

    try {
      PostResponseDto responseDto = postService.getUser(postId);
      return ResponseEntity.ok().body(responseDto);
    } catch (BusinessException be) {
      return ResponseEntity.status(be.getStatus())
          .body(new CommonResponseDto(be.getMessage(), be.getStatus()));
    }
  }

  // 게시글 전체 조회
//  @GetMapping
//  public ResponseEntity<List<PostResponseDto>> getPosts() {
//    List<PostResponseDto> responseDto = postService.getPosts();
//    return ResponseEntity.ok(responseDto);
//  }

  // 게시글 페이징 조회
  @GetMapping
  public ResponseEntity<Page<PostResponseDto>> getPosts(
      @RequestParam("page") int page,
      @RequestParam("size") int size,
      @RequestParam("sortKey") String sortKey,
      @RequestParam("isAsc") boolean isAsc) {

    Page<PostResponseDto> responseDtoPage = postService.getPosts(
        page - 1, size, sortKey, isAsc);
    return ResponseEntity.ok(responseDtoPage);
  }

  // 게시글 수정
  @PatchMapping("/{postId}")
  public ResponseEntity<?> updatePost(
      @Valid @RequestBody PostUpdateRequestDto requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PathVariable Long postId) {

    User loginUser = userDetails.getUser();

    try {
      PostResponseDto responseDto = postService.updatePost(requestDto, loginUser, postId);
      return ResponseEntity.ok().body(responseDto);
    } catch (BusinessException be) {
      return ResponseEntity.status(be.getStatus())
          .body(new CommonResponseDto(be.getMessage(), be.getStatus()));
    }
  }

  // 게시글 삭제
  @DeleteMapping("{postId}")
  public ResponseEntity<?> deletePost(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PathVariable Long postId) {

    User loginUser = userDetails.getUser();

    try {
      postService.deletePost(loginUser, postId);
      return ResponseEntity.ok()
          .body(new CommonResponseDto("삭제 완료", HttpStatus.OK.value()));
    } catch (BusinessException be) {
      return ResponseEntity.status(be.getStatus())
          .body(new CommonResponseDto(be.getMessage(), be.getStatus()));
    }
  }
}
