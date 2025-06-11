package com.example.sideproject.infra.security.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
data class JwtProperties(
    @Value("\${jwt.secret}")
    val secret: String,
    @Value("\${jwt.access-token.expiration-time}")
    val accessTokenExpirationTime: Long,
    @Value("\${jwt.refresh-token.expiration-time}")
    val refreshTokenExpirationTime: Long
)
