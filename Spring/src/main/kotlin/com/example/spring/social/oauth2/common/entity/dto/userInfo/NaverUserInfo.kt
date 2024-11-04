package com.example.spring.social.oauth2.common.entity.dto.userInfo

class NaverUserInfo():OAuth2UserInfo {

    var userInfo : MutableMap<String, Any> = mutableMapOf()

    override fun getProviderId(): String {
        return userInfo["id"].toString()
    }

    override fun getProvider(): String {
        return "naver"
    }

    override fun getName(): String {
        return userInfo["name"].toString()
    }
}