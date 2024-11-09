package com.example.spring.domain.base.error

import org.springframework.http.HttpStatus

class ErrorReasonDto(
    var httpStatus : HttpStatus?=null,
    val isSucess : Boolean,
    val code : String,
    val message : String
) {
}