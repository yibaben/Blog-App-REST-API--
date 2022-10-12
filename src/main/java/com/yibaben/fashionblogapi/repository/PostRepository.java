package com.yibaben.fashionblogapi.repository;

import com.yibaben.fashionblogapi.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByTitleContainingIgnoreCase(String keyword);
}
