package com.yibaben.fashionblogapi.responses;

import com.yibaben.fashionblogapi.models.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class SearchPostResponse {

    private String message;
    private LocalDateTime timeStamp;
    private List<Post> posts;
}
