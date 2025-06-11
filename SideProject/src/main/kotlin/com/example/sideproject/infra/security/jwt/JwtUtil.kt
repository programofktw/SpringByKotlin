package com.example.sideproject.infra.security.jwt

import io.jsonwebtoken.Claims
import jakarta.servlet.http.HttpServletRequest
import java.util.*

interface JwtUtil {

    fun generateAccessToken(uuid: UUID) : String

    fun generateRefreshToken(uuid : UUID) : String

    fun getTokenFromHeader(httpServletRequest: HttpServletRequest): String

    fun getUserIdFromToken(token: String): String



    fun signCheck(token : String) :Boolean
}

