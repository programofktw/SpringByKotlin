package com.example.sideproject.infra.security.jwt

import io.jsonwebtoken.Claims
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtilImpl(

    @Value("\${jwt.secret}")
    val SECRET_KEY : String,

    @Value("\${jwt.access-token.expiration-time}")
    val ACCESS_TOKEN_EXPIRATION_TIME : Long,

    @Value("\${jwt.refresh-token.expiration-time}")
    val REFRESH_TOKEN_EXPIRATION_TIME : Long


) : JwtUtil {
    var log : Logger = LoggerFactory.getLogger(this::class.java)



    override fun generateAccessToken(uuid: UUID, expirationMillis: Long, isSign: Boolean) {
        TODO("Not yet implemented")
    }

    override fun generateRefreshToken(uuid: UUID, expirationMillis: Long) {
        TODO("Not yet implemented")
    }

    override fun getTokenFromHeader(httpServletRequest: HttpServletRequest) {
        TODO("Not yet implemented")
    }

    override fun getUserIdFromToken(token: String?): String {
        TODO("Not yet implemented")
    }

    override fun getClaimsFromToken(token: String?): Claims {
        TODO("Not yet implemented")
    }

    override fun validateToken(token: String) {
        TODO("Not yet implemented")
    }

    override fun signCheck(token: String): Boolean {
        TODO("Not yet implemented")
    }


}