package com.example.spring.domain.token.error.enums

import com.example.spring.domain.base.error.BaseErrorCode
import com.example.spring.domain.base.error.ErrorReasonDto
import org.springframework.http.HttpStatus


enum class TokenErrorResult(
    val httpStatus: HttpStatus?= null,
    val code : String,
    val message : String
):BaseErrorCode {
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "401", "유효하지 않은 토큰입니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "401", "유효하지 않은 액세스 토큰입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "401", "유효하지 않은 리프레쉬 토큰입니다.");

    override fun getReason(): ErrorReasonDto {
        return ErrorReasonDto(isSucess = false, code = code, message = message)

    }

    override fun getReasonHttpStatus(): ErrorReasonDto {
        return ErrorReasonDto(httpStatus =httpStatus,isSucess = false, code= code, message = message)
    }

}