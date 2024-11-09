package com.example.spring.domain.base.response.enums

import com.example.spring.domain.base.error.BaseCode
import com.example.spring.domain.base.error.ReasonDto
import org.springframework.http.HttpStatus

enum class SuccessStatus(
    val httpStatus : HttpStatus,
    val code : String,
    val message : String
):BaseCode {
    // 전역 응답 코드
    _OK(HttpStatus.OK, "200", "성공입니다."),
    _CREATED(HttpStatus.CREATED, "201", "생성에 성공했습니다."),

    // 커스텀 응답 코드
    _CREATED_ACCESS_TOKEN(HttpStatus.CREATED, "201", "액세스 토큰 재발행에 성공했습니다.");

    override fun getReason() : ReasonDto{
        return ReasonDto( code = code, message = message, isSucess = true)
    }

    override fun getReasonHttpStatus() : ReasonDto{
        return ReasonDto(httpStatus=httpStatus, code=code, message = message, isSucess = true)
    }

}