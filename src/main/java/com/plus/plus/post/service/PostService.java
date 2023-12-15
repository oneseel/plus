package com.plus.plus.post.service;

import com.plus.plus.global.exception.post.PostNotFoundException;
import com.plus.plus.global.exception.user.AuthenticationMismatchException;
import com.plus.plus.post.dto.PostRequestDto;
import com.plus.plus.post.dto.PostResponseDto;
import com.plus.plus.post.dto.PostUpdateRequestDto;
import com.plus.plus.post.entity.Post;
import com.plus.plus.post.repository.PostRepository;
import com.plus.plus.user.entity.User;
import java.util.List;
import java.util.Objects;
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

  public List<PostResponseDto> getPosts() {
    return postRepository.findAllByOrderByCreatedDateDesc()
        .stream().map(PostResponseDto::new).toList();
  }

  public PostResponseDto updatePost(
      PostUpdateRequestDto requestDto, User loginUser, Long postId) {

    Post post = getPost(postId);

    // 로그인 한 유저와 작성자가 일치하는지 확인
    if (!Objects.equals(loginUser.getUsername(), post.getAuthor())) {
      throw new AuthenticationMismatchException();
    }

   post.update(requestDto);
    return new PostResponseDto(post);
  }

  // postId에 해당하는 게시글을 가지고 오는 메서드
  public Post getPost(Long postId) {
    return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
  }
}
