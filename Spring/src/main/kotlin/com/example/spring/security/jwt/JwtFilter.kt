package com.example.spring.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.GenericFilter
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse

class JwtFilter(
    val jwtUtil : JwtUtil
):GenericFilter() {
    val AUTHORIZATION_HEADER : String = "Authorization";


    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        TODO("Not yet implemented")
    }
}