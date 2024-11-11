package com.example.spring.domain.user.error.exception

import java.lang.RuntimeException

class UserException(
    val userErrorResult : UserErrorResult
) : RuntimeException() {

}