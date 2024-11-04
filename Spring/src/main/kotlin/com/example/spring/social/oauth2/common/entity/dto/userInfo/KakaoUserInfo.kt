package com.example.spring.social.oauth2.common.entity.dto.userInfo

import java.util.Objects

class KakaoUserInfo(): OAuth2UserInfo {

    var userInfo : MutableMap<String, Any> = mutableMapOf()

    override fun getProviderId(): String {
        return userInfo.get("id").toString();
    }

    override fun getProvider(): String {
        return "kakao"
    }

    override fun getName(): String {
        return (userInfo["properties"] as? Map<*, *>)?.get("nickname") as String
    }
}