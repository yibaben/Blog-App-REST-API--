package com.yibaben.fashionblogapi.exceptions;

import lombok.Data;

@Data
public class PostAlreadyLikedException extends RuntimeException{

    private String message;

    public PostAlreadyLikedException(String message) {
        this.message = message;
    }
}
