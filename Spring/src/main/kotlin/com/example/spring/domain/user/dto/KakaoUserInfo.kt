package com.example.spring.domain.user.dto


class KakaoUserInfo(
    private var attributes: Map<String, Any>
) : UserInfoDto {


    override fun getProviderId(): String {
        return attributes["id"].toString()
    }

    override fun getProvider(): String {
        return "kakao"
    }

    override fun getName() :String{

        return (attributes["properties"] as Map<*, *>)["nickname"].toString()

    }
}