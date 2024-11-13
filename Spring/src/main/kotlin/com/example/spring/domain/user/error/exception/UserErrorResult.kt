package com.example.spring.domain.user.error.exception

import com.example.spring.domain.base.error.BaseErrorCode
import com.example.spring.domain.base.error.ErrorReasonDto
import org.springframework.http.HttpStatus

enum class UserErrorResult(
    val httpStatus : HttpStatus? = null,
    val code : String,
    val message : String
) : BaseErrorCode {

    _NOT_FOUND_USER(HttpStatus.NOT_FOUND, "404", "존재하지 않는 유저입니다."),

    _NOT_AUTHORITY_USER(HttpStatus.UNAUTHORIZED,"401","유저의 권한이 잘못되었습니다.");



      override fun getReason() : ErrorReasonDto {
        return ErrorReasonDto(isSucess = false, code= code, message = message)
    }


    override fun getReasonHttpStatus() :ErrorReasonDto {
        return ErrorReasonDto(isSucess = false, httpStatus = httpStatus, code= code, message = message)
    }

}