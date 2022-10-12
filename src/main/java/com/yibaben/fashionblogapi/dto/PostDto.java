package com.yibaben.fashionblogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDto {
    private String title;
    private String description;
    private String postImage;
    private int user_id;
}
