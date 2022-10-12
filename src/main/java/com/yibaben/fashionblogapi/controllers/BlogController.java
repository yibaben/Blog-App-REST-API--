package com.yibaben.fashionblogapi.controllers;

import com.yibaben.fashionblogapi.dto.CommentDto;
import com.yibaben.fashionblogapi.dto.LikeDto;
import com.yibaben.fashionblogapi.dto.PostDto;
import com.yibaben.fashionblogapi.dto.UserDto;
import com.yibaben.fashionblogapi.models.Post;
import com.yibaben.fashionblogapi.responses.*;
import com.yibaben.fashionblogapi.services.Services;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class BlogController {

    private final Services services;

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody UserDto userDto){
        log.info("Successfully Registered {} " , userDto.getEmail());
        return new ResponseEntity<>(services.register(userDto), CREATED);

    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreatePostResponse> create(@RequestBody PostDto postDto){
        log.info("Successfully Created A post With Title:  {} " , postDto.getTitle());
        return new ResponseEntity<>(services.createPost(postDto), CREATED);

    }

    @PostMapping(value = "/comment/{user_id}/{post_id}")
    public ResponseEntity<CommentResponse> comment(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value = "post_id") Integer post_id, @RequestBody CommentDto commentDto){
        log.info("Successfully commented :  {} " , commentDto.getComment());
        return new ResponseEntity<>(services.comment(user_id, post_id, commentDto), CREATED);

    }

    @PostMapping(value = "/like/{user_id}/{post_id}")
    public ResponseEntity<LikeResponse> like(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value = "post_id") Integer post_id, @RequestBody LikeDto likeDto){
        log.info("Successfully liked :  {} " , likeDto.isLiked());
        return new ResponseEntity<>(services.like(user_id, post_id, likeDto), CREATED);

    }

    @GetMapping(value = "/searchPost/{keyword}")
    public ResponseEntity<SearchPostResponse> searchPost(@PathVariable(  value = "keyword") String keyword){
        return new ResponseEntity<>(services.searchPost(keyword), OK);

    }

    @GetMapping(value = "/searchComment/{keyword}")
    public ResponseEntity<SearchCommentResponse> searchComment(@PathVariable(  value = "keyword") String keyword){
        return ResponseEntity.ok().body(services.searchComment(keyword));
    }

    @GetMapping(value = "/post/{id}")
    public ResponseEntity<Post> searchComment(@PathVariable(  value = "id") Integer id){
        return ResponseEntity.ok().body(services.findPostById(id));
    }
}
