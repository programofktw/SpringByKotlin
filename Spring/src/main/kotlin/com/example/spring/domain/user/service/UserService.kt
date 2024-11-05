package com.example.spring.domain.user.service

import com.example.spring.domain.user.dto.UserInfoDto
import org.springframework.stereotype.Service

interface UserService {
    fun findUserInfo(authorizationHeader : String) : UserInfoDto
}