package com.example.spring.domain.user.dto

interface OAuth2UserInfoDto{
    fun getProviderId() : String;

    fun getProvider() : String;

    fun getName() : String;
}