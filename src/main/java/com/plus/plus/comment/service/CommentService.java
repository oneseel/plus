package com.plus.plus.comment.service;

import com.plus.plus.comment.dto.CommentRequestDto;
import com.plus.plus.comment.dto.CommentResponseDto;
import com.plus.plus.comment.entity.Comment;
import com.plus.plus.comment.repository.CommentRepository;
import com.plus.plus.post.entity.Post;
import com.plus.plus.post.service.PostService;
import com.plus.plus.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;

  private final PostService postService;

  public CommentResponseDto createComment(User loginuser, CommentRequestDto requestDto, Long postId) {

    Post post = postService.getPost(postId);
    Comment comment = new Comment(loginuser, requestDto, post);
    return new CommentResponseDto(commentRepository.save(comment));
  }
}
