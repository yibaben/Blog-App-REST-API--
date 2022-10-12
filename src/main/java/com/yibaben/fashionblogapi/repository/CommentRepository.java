package com.yibaben.fashionblogapi.repository;

import com.yibaben.fashionblogapi.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByCommentContainingIgnoreCase(String keyword);
}
