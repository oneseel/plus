package com.plus.plus.comment.service;

import com.plus.plus.comment.dto.CommentRequestDto;
import com.plus.plus.comment.dto.CommentResponseDto;
import com.plus.plus.comment.dto.CommentUpdateRequestDto;
import com.plus.plus.comment.entity.Comment;
import com.plus.plus.comment.repository.CommentRepository;
import com.plus.plus.global.exception.comment.CommentNotFoundException;
import com.plus.plus.global.exception.post.PostNotFoundException;
import com.plus.plus.global.exception.user.AuthenticationMismatchException;
import com.plus.plus.post.dto.PostResponseDto;
import com.plus.plus.post.entity.Post;
import com.plus.plus.post.service.PostService;
import com.plus.plus.user.entity.User;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;

  private final PostService postService;

  @Transactional
  public CommentResponseDto createComment(User loginuser, CommentRequestDto requestDto, Long postId) {

    Post post = postService.getPost(postId);
    Comment comment = new Comment(loginuser, requestDto, post);
    return new CommentResponseDto(commentRepository.save(comment));
  }

  public List<CommentResponseDto> getComments(Long postId) {

    postService.getPost(postId);
    return commentRepository.findAll().stream().map(CommentResponseDto::new).toList();
  }

  @Transactional
  public CommentResponseDto updateComment(User loginUser, CommentUpdateRequestDto requestDto, Long postId, Long commentId) {

    postService.getPost(postId);
    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(CommentNotFoundException::new);
    getUser(loginUser,comment);

    comment.update(requestDto);
    return new CommentResponseDto(comment);
  }

  // 로그인 한 유저와 작성자가 일치하는지 확인하는 메서드
  private void getUser(User loginUser, Comment comment) {
    if (!Objects.equals(loginUser.getUsername(), comment.getAuthor())) {
      throw new AuthenticationMismatchException();
    }
  }
}
