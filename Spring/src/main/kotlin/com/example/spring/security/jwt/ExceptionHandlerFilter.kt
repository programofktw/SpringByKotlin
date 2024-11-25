package com.example.spring.security.jwt

import com.example.spring.domain.token.error.enums.TokenErrorResult
import com.example.spring.domain.token.error.exception.TokenException
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.GenericFilter
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import java.io.IOException


class ExceptionHandlerFilter():GenericFilter() {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        try {
            chain!!.doFilter(request, response)
        } catch (e: TokenException) {
            //토큰 문제
            setErrorResponse(response, TokenErrorResult.INVALID_ACCESS_TOKEN)
        }
    }

    fun setErrorResponse(response: ServletResponse?, tokenResult : TokenErrorResult){
        val objectMapper = ObjectMapper()
        if(response is HttpServletResponse){
            response.status = 401
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.characterEncoding = "UTF-8"
            val errorResponse = ErrorResponse(code = tokenResult.code,message= tokenResult.message)
            try {
                response.writer.write(objectMapper.writeValueAsString(errorResponse))
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }


    class ErrorResponse(
        val code : String,
        val message : String
    ) {

    }


}