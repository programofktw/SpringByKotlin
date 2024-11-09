package com.example.spring.domain.base.error

interface BaseCode {
    fun getReason() : ReasonDto

    fun getReasonHttpStatus() : ReasonDto
}