package com.plus.plus.post.repository;

import com.plus.plus.post.entity.Post;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

  List<Post> findAllByOrderByCreatedDateDesc(); // 만든날짜를 기준으로 내림차순

  void deleteByModifiedDateBefore(LocalDateTime ninetyDaysAgo);
}
