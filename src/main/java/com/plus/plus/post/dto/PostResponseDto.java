package com.plus.plus.post.dto;

import com.plus.plus.post.entity.Post;
import java.time.LocalDateTime;
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

  public PostResponseDto(Post savePost) {
    this.id = savePost.getId();
    this.title = savePost.getTitle();
    this.content = savePost.getContents();
    this.author = savePost.getAuthor();
    this.likeCount = savePost.getLikeCount();
    this.createdDate = savePost.getCreatedDate();
    this.modifiedDate = savePost.getModifiedDate();
  }
}
