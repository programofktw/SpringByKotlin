package com.example.sideproject.infra.security.jwt

import io.jsonwebtoken.Claims
import jakarta.servlet.http.HttpServletRequest
import java.util.*

interface JwtUtil {

    fun generateAccessToken(uuid: UUID, expirationMillis: Long, isSign : Boolean)

    fun generateRefreshToken(uuid : UUID, expirationMillis: Long)

    fun getTokenFromHeader(httpServletRequest: HttpServletRequest)

    fun getUserIdFromToken(token: String?): String

    fun getClaimsFromToken(token : String?) : Claims

    fun validateToken(token : String)

    fun signCheck(token : String) :Boolean
}

