package com.example.spring

import com.example.spring.domain.base.error.BaseErrorCode
import com.example.spring.domain.base.response.web.ApiResponse
import com.example.spring.domain.token.error.exception.TokenException
import com.example.spring.domain.user.error.exception.UserErrorResult
import com.example.spring.domain.user.error.exception.UserException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(TokenException::class)
    fun handleTokenException(e: TokenException): ResponseEntity<ApiResponse<BaseErrorCode?>> {
        val errorResult = e.tokenErrorResult
        return ApiResponse.onFailure(errorResult!!)
    }

    @ExceptionHandler(UserException::class)
    fun handleUserException(e: UserException): ResponseEntity<ApiResponse<BaseErrorCode?>> {
        val errorResult: UserErrorResult = e.userErrorResult
        return ApiResponse.onFailure(errorResult)
    }
}