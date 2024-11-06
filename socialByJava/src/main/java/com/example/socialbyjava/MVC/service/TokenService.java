package com.example.socialbyjava.MVC.service;


import com.example.socialbyjava.etc.response.TokenResponse;

public interface TokenService {
    TokenResponse reissueAccessToken(String authorizationHeader);
}

