package com.example.spring.domain.token.error.exception

import com.example.spring.domain.token.error.enums.TokenErrorResult
import java.lang.RuntimeException

class TokenException(
    val tokenErrorResult : TokenErrorResult
): RuntimeException() {


}