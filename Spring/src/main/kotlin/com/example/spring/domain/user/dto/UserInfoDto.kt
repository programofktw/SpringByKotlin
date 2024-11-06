package com.example.spring.domain.user.dto

interface UserInfoDto{
    fun getProviderId() : String;

    fun getProvider() : String;

    fun getName() : String;
}