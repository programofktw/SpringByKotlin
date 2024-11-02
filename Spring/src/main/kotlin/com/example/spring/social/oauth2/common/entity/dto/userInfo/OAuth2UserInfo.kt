package com.example.spring.social.oauth2.common.entity.dto.userInfo

interface OAuth2UserInfo {
    fun getProviderId() : String
    fun getProvider() : String
    fun getName() : String
}