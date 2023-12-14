package com.plus.plus.post.entity;

import com.plus.plus.common.Timestamped;
import com.plus.plus.post.dto.PostRequestDto;
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
@Entity(name = "posts")
public class Post extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String contents;

  @Column(nullable = false)
  private String author;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Post(User loginUser,PostRequestDto requestDto) {
    this.title = requestDto.getTitle();
    this.contents = requestDto.getContent();
    this.author = loginUser.getUsername();
    this.user = loginUser;
  }
}
