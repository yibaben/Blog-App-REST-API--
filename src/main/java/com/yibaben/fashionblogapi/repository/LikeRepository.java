package com.yibaben.fashionblogapi.repository;

import com.yibaben.fashionblogapi.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {


//    Optional<Like> findLikeByUserIdAndPostId(int user_id, int post_id);

    @Query(value = "SELECT * FROM likes WHERE user_id = ?1 AND post_id = ?2", nativeQuery = true)
    Like findLikeByUserIdAndPostId(int user_id , int post_id);

    @Query(value = "SELECT  * FROM likes WHERE   post_id = ?1", nativeQuery = true)
    List<Like> likeList(int post_id);


}
