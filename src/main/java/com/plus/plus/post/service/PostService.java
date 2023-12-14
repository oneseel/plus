package com.plus.plus.post.service;

import com.plus.plus.post.dto.PostRequestDto;
import com.plus.plus.post.dto.PostResponseDto;
import com.plus.plus.post.entity.Post;
import com.plus.plus.post.repository.PostRepository;
import com.plus.plus.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public PostResponseDto createPost(User loginUser, PostRequestDto requestDto) {
    Post post = new Post(loginUser, requestDto);
    return new PostResponseDto(postRepository.save(post));
  }
}
