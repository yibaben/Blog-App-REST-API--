package com.yibaben.fashionblogapi.responses;

import com.yibaben.fashionblogapi.models.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreatePostResponse {

    private String message;
    private LocalDateTime timeStamp;
    private Post post;
}
