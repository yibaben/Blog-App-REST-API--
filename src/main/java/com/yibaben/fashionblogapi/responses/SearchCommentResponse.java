package com.yibaben.fashionblogapi.responses;

import com.yibaben.fashionblogapi.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class SearchCommentResponse {

    private String message;
    private LocalDateTime timeStamp;
    private List<Comment> comments;
}
