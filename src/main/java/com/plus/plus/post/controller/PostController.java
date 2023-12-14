package com.plus.plus.post.controller;

import com.plus.plus.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

  private final PostService postService;

  // 게시글 작성

  // 게시글 단건 조회

  // 게시글 전체 조회

  // 게시글 수정

  // 게시글 삭제
}
