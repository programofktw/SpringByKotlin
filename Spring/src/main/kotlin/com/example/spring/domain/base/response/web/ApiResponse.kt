package com.example.spring.domain.base.response.web

import com.example.spring.domain.base.error.BaseCode
import com.example.spring.domain.base.error.BaseErrorCode
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.ResponseEntity


class ApiResponse<T>(
    @JsonProperty("isSuccess")
    val isSuccess: Boolean? = null,
    val code: String? = null,
    val message: String? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val payload: T? = null
) {
    companion object{
        fun <T> onSuccess(code: BaseCode, data: T): ResponseEntity<ApiResponse<T?>> {
            val response: ApiResponse<T?> =
                ApiResponse<T?>(true, code.getReasonHttpStatus().code, code.getReasonHttpStatus().message, data)
            return ResponseEntity.status(code.getReasonHttpStatus().httpStatus!!).body(response)
        }

        fun <T> onFailure(code: BaseErrorCode): ResponseEntity<ApiResponse<T?>> {
            val response: ApiResponse<T?> =
                ApiResponse<T?>(false, code.getReasonHttpStatus()!!.code, code.getReasonHttpStatus()!!.message, null)
            return ResponseEntity.status(code.getReasonHttpStatus()!!.httpStatus!!).body(response)
        }
    }


}