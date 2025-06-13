package com.example.sideproject.infra.security.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtProperties{

    //NOTE("AccessToken과 RefreshToken의 시크릿 분리")
    lateinit var accessToken : AccessTokenProperties
    lateinit var refreshToken : ResfreshTokenProperties
}
