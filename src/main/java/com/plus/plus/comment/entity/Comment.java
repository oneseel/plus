package com.plus.plus.comment.entity;

import com.plus.plus.comment.dto.CommentRequestDto;
import com.plus.plus.common.Timestamped;
import com.plus.plus.post.entity.Post;
import com.plus.plus.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "comments")
public class Comment extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private String author;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  public Comment(User loginuser, CommentRequestDto requestDto, Post post) {
    this.content = requestDto.getContent();
    this.author = loginuser.getUsername();
    this.user = loginuser;
    this.post = post;
  }
}
