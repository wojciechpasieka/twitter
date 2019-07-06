package com.twitter.demo.repository;

import com.twitter.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p ORDER BY createDate DESC")
    List<Post> findAllByCreateDateOrderByDesc();
}
