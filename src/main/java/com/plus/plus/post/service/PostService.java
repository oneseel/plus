package com.plus.plus.post.service;

import com.plus.plus.global.exception.post.PostNotFoundException;
import com.plus.plus.global.exception.user.AuthenticationMismatchException;
import com.plus.plus.post.dto.PostRequestDto;
import com.plus.plus.post.dto.PostResponseDto;
import com.plus.plus.post.dto.PostUpdateRequestDto;
import com.plus.plus.post.entity.Post;
import com.plus.plus.post.repository.PostRepository;
import com.plus.plus.user.entity.User;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  @Transactional
  public PostResponseDto createPost(User loginUser, PostRequestDto requestDto) {
    Post post = new Post(loginUser, requestDto);
    return new PostResponseDto(postRepository.save(post));
  }

  public PostResponseDto getUser(Long postId) {
    Post post = getPost(postId);
    return new PostResponseDto(post);
  }

//  public List<PostResponseDto> getPosts() {
//    return postRepository.findAllByOrderByCreatedDateDesc()
//        .stream().map(PostResponseDto::new).toList();
//  }

  public Page<PostResponseDto> getPosts(int page, int size, String sortKey, boolean isAsc) {
    // 페이징 처리
    Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
    Sort sort = Sort.by(direction, sortKey);
    Pageable pageable = PageRequest.of(page, size, sort);

    Page<Post> postList;
    postList = postRepository.findAll(pageable);
    return postList.map(PostResponseDto::new);
  }

  @Transactional
  public PostResponseDto updatePost(
      PostUpdateRequestDto requestDto, User loginUser, Long postId) {

    Post post = getPost(postId);
    getUser(loginUser, post);
    post.update(requestDto);
    return new PostResponseDto(post);
  }

  @Transactional
  public void deletePost(User loginUser, Long postId) {

    Post post = getPost(postId);
    getUser(loginUser, post);
    postRepository.delete(post);
  }

  // postId에 해당하는 게시글을 가지고 오는 메서드
  public Post getPost(Long postId) {
    return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
  }

  // 로그인 한 유저와 작성자가 일치하는지 확인하는 메서드
  public void getUser(User loginUser, Post post) {
    if (!Objects.equals(loginUser.getUsername(), post.getAuthor())) {
      throw new AuthenticationMismatchException();
    }
  }
}
