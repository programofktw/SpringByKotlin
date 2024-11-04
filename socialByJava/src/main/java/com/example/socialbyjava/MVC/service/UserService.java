package com.example.socialbyjava.MVC.service;

import com.example.socialbyjava.etc.response.UserResponse;

public interface UserService {
    UserResponse getUserInfo(String authorizationHeader);
}
