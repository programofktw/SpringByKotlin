package com.example.spring.domain.user.dto

import java.util.*

class UserInfoDto(

    var uuid : UUID,

    var name : String,

    var providerId : String,

    var provider : String
) {
}