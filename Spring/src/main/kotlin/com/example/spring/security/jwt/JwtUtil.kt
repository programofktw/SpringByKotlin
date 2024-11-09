package com.example.spring.security.jwt

import com.example.spring.domain.token.error.enums.TokenErrorResult
import com.example.spring.domain.token.error.exception.TokenException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey


@Component
@PropertySource("classpath:/secret.yml")
class JwtUtil {
    var log : Logger = LoggerFactory.getLogger(this::class.java)
    @Value("\${jwt.secret}")
    lateinit var SECRET_KEY : String

    private fun getSigningKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    // 액세스 토큰을 발급하는 메서드
    fun generateAccessToken(uuid: UUID, expirationMillis: Long): String {
        log.info("액세스 토큰이 발행되었습니다.")
        return Jwts.builder()
            .claim("userId", uuid.toString()) // 클레임에 userId 추가
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expirationMillis))
            .signWith(getSigningKey())
            .compact()
    }

    // 리프레쉬 토큰을 발급하는 메서드
    fun generateRefreshToken(uuid : UUID, expirationMillis: Long): String {
        log.info("리프레쉬 토큰이 발행되었습니다.")
        return Jwts.builder()
            .claim("userId", uuid.toString()) // 클레임에 userId 추가
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expirationMillis))
            .signWith(getSigningKey())
            .compact()
    }

    // 응답 헤더에서 액세스 토큰을 반환하는 메서드
    fun getTokenFromHeader(authorizationHeader: String): String {
        return authorizationHeader.substring(7)
    }

    // 토큰에서 유저 id를 반환하는 메서드
    fun getUserIdFromToken(token: String?): String {
        return try {
            val userId: String = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .body
                .get("userId", String::class.java)
            log.info("유저 id를 반환합니다.")
            userId
        } catch (e: JwtException) {
            // 토큰이 유효하지 않은 경우
            log.warn("유효하지 않은 토큰입니다.")
            throw TokenException(TokenErrorResult.INVALID_TOKEN)
        } catch (e: IllegalArgumentException) {
            log.warn("유효하지 않은 토큰입니다.")
            throw TokenException(TokenErrorResult.INVALID_TOKEN)
        }
    }
}