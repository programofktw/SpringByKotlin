package com.example.spring.email.mvc.service

import org.springframework.stereotype.Service


@Service
interface EmailVerificationService {
    //도메인 확인
    fun checkDomain(domain : String)
    //인증 코드 확인
    fun checkCode(code : String)
    //인증 코드 요청
    fun requestCode() : String
}