package com.yibaben.fashionblogapi.responses;

import com.yibaben.fashionblogapi.models.Comment;
import com.yibaben.fashionblogapi.models.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentResponse {

    private String message;
    private LocalDateTime timeStamp;
    private Comment comment;
    private Post post;
}
