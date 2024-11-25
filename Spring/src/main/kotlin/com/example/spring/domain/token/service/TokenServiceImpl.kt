package com.example.spring.domain.token.service

import com.example.spring.domain.token.Tokens
import com.example.spring.domain.token.error.enums.TokenErrorResult
import com.example.spring.domain.token.error.exception.TokenException
import com.example.spring.domain.token.refreshtoken.entity.RefreshToken
import com.example.spring.domain.token.refreshtoken.repository.RefreshTokenRepository
import com.example.spring.security.jwt.JwtUtil
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*


@Service
public class TokenServiceImpl(
    @Value("\${jwt.refresh-token.expiration-time}")
    private val REFRESH_TOKEN_EXPIRATION_TIME: Long, // 리프레쉬 토큰 유효기간

    @Value("\${jwt.access-token.expiration-time}")
    private val ACCESS_TOKEN_EXPIRATION_TIME: Long, // 액세스 토큰 유효기간

    val refreshTokenRepository: RefreshTokenRepository,
    val jwtUtil : JwtUtil,

):TokenService {
    override fun ReissueAccessTokenByRefreshToken(accessToken: String): Tokens {
       try{
           jwtUtil.validateToken(accessToken)
       }catch (e : Exception){
           throw TokenException(TokenErrorResult.INVALID_REFRESH_TOKEN)
       }

        val userId : UUID = UUID.fromString(jwtUtil.getUserIdFromToken(accessToken))


        refreshTokenRepository.deleteByUserId(userId);

        val refreshToken = jwtUtil.generateRefreshToken(userId,REFRESH_TOKEN_EXPIRATION_TIME)

        val newRefreshToken : RefreshToken = RefreshToken(userId = userId, token = refreshToken)

        refreshTokenRepository.save(newRefreshToken)

        val accessToken : String = jwtUtil.generateAccessToken(userId, ACCESS_TOKEN_EXPIRATION_TIME)

        return Tokens(accessToken=accessToken, refreshToken = refreshToken)
    }
    fun createCookies(key : String, value : String) : Cookie {
        val cookie = Cookie(key, value)
        cookie.path = "/"
        cookie.isHttpOnly = false
        cookie.secure = false
        cookie.maxAge = 3600

        return cookie
    }

}