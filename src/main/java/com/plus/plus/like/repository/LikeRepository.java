package com.plus.plus.like.repository;

import com.plus.plus.like.entity.Like;
import com.plus.plus.post.entity.Post;
import com.plus.plus.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

  Optional<Like> findByUserAndPost(User loginUser, Post post);
}
