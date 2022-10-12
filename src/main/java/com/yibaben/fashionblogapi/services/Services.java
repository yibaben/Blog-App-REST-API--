package com.yibaben.fashionblogapi.services;


import com.yibaben.fashionblogapi.dto.*;
import com.yibaben.fashionblogapi.models.Post;
import com.yibaben.fashionblogapi.responses.*;

public interface Services {

    RegisterResponse register(UserDto userDto);

    LoginResponse login(LoginDto loginDto);

    CreatePostResponse createPost(PostDto postDto);

    CommentResponse comment(int user_id , int post_id , CommentDto commentDto);

    LikeResponse like(int user_id , int post_id , LikeDto likeDto);

    SearchCommentResponse searchComment(String keyword);


    SearchPostResponse searchPost(String keyword);

    Post findPostById(int id);

}
