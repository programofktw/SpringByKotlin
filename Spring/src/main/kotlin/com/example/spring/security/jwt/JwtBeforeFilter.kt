package com.example.spring.security.jwt

import com.example.spring.domain.token.error.enums.TokenErrorResult
import com.example.spring.domain.token.error.exception.TokenException
import jakarta.servlet.FilterChain
import jakarta.servlet.GenericFilter
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import org.antlr.v4.runtime.Token
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import reactor.netty.http.Cookies

class JwtBeforeFilter(
    val jwtUtil : JwtUtil
):GenericFilter() {
    var log : Logger = LoggerFactory.getLogger(this::class.java)
    val AUTHORIZATION_HEADER : String = "Authorization"
    val ACCESSTOKEN_COOKIE : String = "AccessToken"

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        var httpServeletRequest : HttpServletRequest = request as HttpServletRequest

        try{
            val cookies = request.cookies
            log.info(request.getHeader(AUTHORIZATION_HEADER))
//            val token : String = jwtUtil.getTokenFromHeader(request.getHeader(AUTHORIZATION_HEADER))
            var hasToken : Boolean = false
//            log.info(token)
            if (cookies != null) {
                for(cookie in cookies){
                    if(cookie.name.equals(ACCESSTOKEN_COOKIE)) hasToken = true
                }
                if(!hasToken){
                    throw TokenException(TokenErrorResult.INVALID_ACCESS_TOKEN)
                }
            }
            else{
                throw TokenException(TokenErrorResult.INVALID_ACCESS_TOKEN)
//                log.info("cookie가 없어요")
            }
//            if(jwtUtil.isExpired(token))
//                throw TokenException(TokenErrorResult.INVALID_ACCESS_TOKEN)
        }catch (e : TokenException){
            throw e
        }

        chain!!.doFilter(request,response)
    }
}