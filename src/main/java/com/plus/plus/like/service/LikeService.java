package com.plus.plus.like.service;

import com.plus.plus.global.exception.like.DuplicatedLikeException;
import com.plus.plus.global.exception.like.NotFoundLikeException;
import com.plus.plus.global.exception.like.SelfLikeException;
import com.plus.plus.like.entity.Like;
import com.plus.plus.like.repository.LikeRepository;
import com.plus.plus.post.entity.Post;
import com.plus.plus.post.repository.PostRepository;
import com.plus.plus.post.service.PostService;
import com.plus.plus.user.entity.User;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

  private final LikeRepository likeRepository;

  private final PostService postService;

  private final PostRepository postRepository;

  @Transactional
  public Like addLike(User loginUser, Long postId) {

    Post post = postService.getPost(postId);

    // 로그인한 유저와 글 작성자의 불일치 확인
    if (Objects.equals(loginUser.getUsername(), post.getAuthor())) {
      throw new SelfLikeException();
    }

    Like like = Like.fromUserAndPost(loginUser, post);
    likeRepository.save(like);
    post.setLikeCount(post.getLikeCount() + 1);
    postRepository.save(post);
    return like;
  }

  public void cancelLike(User loginUser, Long postId) {

    Post post = postService.getPost(postId);

    // 좋아요 내역이 없는지 확인
    Like like = likeRepository.findByUserAndPost(loginUser, post)
        .orElseThrow(NotFoundLikeException::new);

    likeRepository.deleteById(like.getId());

    post.setLikeCount(post.getLikeCount() - 1);
    postRepository.save(post);
  }
}

