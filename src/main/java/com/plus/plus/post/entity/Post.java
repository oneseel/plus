package com.plus.plus.post.entity;

import com.plus.plus.comment.entity.Comment;
import com.plus.plus.common.Timestamped;
import com.plus.plus.post.dto.PostRequestDto;
import com.plus.plus.post.dto.PostUpdateRequestDto;
import com.plus.plus.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "posts")
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

  @Column
  private int likeCount;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "post")
  private List<Comment> comments;

  public Post(User loginUser,PostRequestDto requestDto) {
    this.title = requestDto.getTitle();
    this.contents = requestDto.getContent();
    this.author = loginUser.getUsername();
    this.user = loginUser;
  }

  public void update(PostUpdateRequestDto requestDto) {
    this.title = requestDto.getTitle();
    this.contents = requestDto.getContent();
  }

}
