package com.example.socialbyjava.MVC.contoller;
import com.example.socialbyjava.MVC.service.TokenService;
import com.example.socialbyjava.etc.enums.SuccessStatus;
import com.example.socialbyjava.etc.response.ApiResponse;
import com.example.socialbyjava.etc.response.TokenResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService authService;

    // 액세스 토큰을 재발행하는 API
    @GetMapping("/reissue/access-token")
    public ResponseEntity<ApiResponse<Object>> reissueAccessToken(
            @RequestHeader("Authorization") String authorizationHeader) {

        TokenResponse accessToken = authService.reissueAccessToken(authorizationHeader);
        return ApiResponse.onSuccess(SuccessStatus._CREATED_ACCESS_TOKEN, accessToken);
    }
}