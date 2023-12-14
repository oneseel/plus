package com.plus.plus.post.service;

import com.plus.plus.global.exception.post.PostNotFoundException;
import com.plus.plus.post.dto.PostRequestDto;
import com.plus.plus.post.dto.PostResponseDto;
import com.plus.plus.post.entity.Post;
import com.plus.plus.post.repository.PostRepository;
import com.plus.plus.user.entity.User;
import java.util.Optional;
import javax.swing.text.html.Option;
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

  public PostResponseDto getUser(Long postId) {
    Post post = getPost(postId);
    return new PostResponseDto(post);
  }


  // postId에 해당하는 게시글을 가지고 오는 메서드
  public Post getPost(Long postId) {
    return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
  }
}
