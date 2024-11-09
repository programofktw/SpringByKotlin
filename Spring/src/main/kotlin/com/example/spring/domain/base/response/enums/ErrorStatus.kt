package com.example.spring.domain.base.response.enums

import com.example.spring.domain.base.error.BaseErrorCode
import com.example.spring.domain.base.error.ErrorReasonDto
import org.springframework.http.HttpStatus

enum class ErrorStatus(
    val httpStatus : HttpStatus,
    val code : String,
    val message : String
):BaseErrorCode{
    // 전역 에러
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"500", "서버에서 요청을 처리 하는 동안 오류가 발생했습니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"400", "입력 값이 잘못된 요청 입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"401", "인증이 필요 합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "403", "금지된 요청 입니다.");

    override fun getReason() : ErrorReasonDto {
        return ErrorReasonDto(isSucess = false, code = code, message = message)
    }


    override fun getReasonHttpStatus() : ErrorReasonDto  {
        return ErrorReasonDto(isSucess = false, httpStatus = httpStatus, code= code, message = message)
    }
}