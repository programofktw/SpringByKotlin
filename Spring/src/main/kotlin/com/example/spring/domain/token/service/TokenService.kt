package com.example.spring.domain.token.service

import com.example.spring.domain.token.Tokens
import jakarta.servlet.http.HttpServletResponse

interface TokenService {
    fun ReissueAccessTokenByRefreshToken(accessToken : String) : Tokens
}