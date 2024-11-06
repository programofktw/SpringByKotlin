package com.example.socialbyjava.MVC.service;


import com.example.socialbyjava.Entity.User;
import com.example.socialbyjava.MVC.repository.UserRepository;
import com.example.socialbyjava.etc.JwtUtil;
import com.example.socialbyjava.etc.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;
    @Override
    public UserResponse getUserInfo(String authorizationHeader) {
        UUID userId = UUID.fromString(jwtUtil.getUserIdFromToken(authorizationHeader));

        User user = userRepository.findByUserId(userId).get();

        UserResponse userResponse = UserResponse.builder()
                .name(user.getName())
                .provider(user.getProvider())
                .build();

        return userResponse;
    }
}
