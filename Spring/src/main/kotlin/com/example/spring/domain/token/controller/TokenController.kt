package com.example.spring.domain.token.controller

import com.example.spring.domain.base.error.BaseCode
import com.example.spring.domain.base.response.web.ApiResponse
import com.example.spring.domain.token.Tokens
import com.example.spring.domain.token.error.enums.TokenErrorResult
import com.example.spring.domain.token.error.exception.TokenException
import com.example.spring.domain.token.service.TokenService
import com.fasterxml.jackson.databind.ser.Serializers.Base
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.antlr.v4.runtime.Token
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/api/token")
class TokenController(
    private val tokenService: TokenService
) {
    var log : Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/test")
    @ResponseBody
    fun testToken() : Map<String,Any>{

        log.info("Token Test Test 시도")
        var result : MutableMap<String, Any> = mutableMapOf()
        result.put("uses", "1234")

        return result
    }
    @GetMapping("/generate")
    fun generateAccessToken(httpServletResponse: HttpServletResponse,@CookieValue(name = "RefreshToken") refreshToken : String): ResponseEntity<Map<String, Any>> {
        var tokens : Tokens
        log.info("AccessToken 갱신 시도")
        try{
           tokens = tokenService.ReissueAccessTokenByRefreshToken(refreshToken)
        }catch (e : Exception){
            throw TokenException(TokenErrorResult.INVALID_REFRESH_TOKEN)
        }

        httpServletResponse.addCookie(createCookies("AccessToken",tokens.accessToken))
        httpServletResponse.addCookie(createCookies("RefreshToken", tokens.refreshToken))
        var body : MutableMap<String, Any> = mutableMapOf()

        body.put("Tokens",tokens)

        var responseEntity : ResponseEntity<Map<String,Any>> = ResponseEntity.ok().body(body)
        return responseEntity
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