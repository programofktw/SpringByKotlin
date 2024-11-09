package com.example.spring.domain.base.error

interface BaseErrorCode {
    fun getReason(): ErrorReasonDto?

    fun getReasonHttpStatus(): ErrorReasonDto?
}