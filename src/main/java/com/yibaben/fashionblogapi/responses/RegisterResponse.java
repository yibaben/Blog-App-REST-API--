package com.yibaben.fashionblogapi.responses;

import com.yibaben.fashionblogapi.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RegisterResponse {

    private String message;
    private LocalDateTime timeStamp;
    private User user;


}
