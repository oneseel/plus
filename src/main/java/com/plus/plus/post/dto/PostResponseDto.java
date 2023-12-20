package com.plus.plus.post.dto;

import com.plus.plus.comment.dto.CommentResponseDto;
import com.plus.plus.post.entity.Post;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponseDto {

  private Long id;
  private String title;
  private String content;
  private String author;
  private int likeCount;
  private LocalDateTime createdDate;
  private LocalDateTime modifiedDate;
  private List<CommentResponseDto> comments;

  public PostResponseDto(Post savePost) {
    this.id = savePost.getId();
    this.title = savePost.getTitle();
    this.content = savePost.getContents();
    this.author = savePost.getAuthor();
    this.likeCount = savePost.getLikeCount();
    this.createdDate = savePost.getCreatedDate();
    this.modifiedDate = savePost.getModifiedDate();
    this.comments = savePost.getComments().stream()
        .map(CommentResponseDto::new)
        .collect(Collectors.toList());
  }
}
