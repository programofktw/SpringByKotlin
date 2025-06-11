package com.example.sideproject.infra.security.jwt

import io.jsonwebtoken.Claims
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtilImpl() : JwtUtil {
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