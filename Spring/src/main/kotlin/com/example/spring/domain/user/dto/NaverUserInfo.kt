package com.example.spring.domain.user.dto

class NaverUserInfo(
    var  attributes : Map<String, Any>
) : UserInfoDto {
    override fun getProviderId(): String {
        return attributes["id"].toString()
    }

    override fun getProvider(): String {
        return "naver"
    }

    override fun getName() : String {
       return attributes["name"].toString()
    }
}