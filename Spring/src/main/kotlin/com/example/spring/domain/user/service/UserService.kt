package com.example.spring.domain.user.service

import com.example.spring.domain.user.dto.UserInfoDto


interface UserService {
    fun findUserInfo(authorizationHeader : String) : UserInfoDto
}