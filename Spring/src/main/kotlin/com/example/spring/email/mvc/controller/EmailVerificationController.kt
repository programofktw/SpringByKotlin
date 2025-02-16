package com.example.spring.email.mvc.controller

import com.example.spring.email.mvc.service.EmailVerificationService
import com.example.spring.security.jwt.JwtUtil
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController("/api/register")
class EmailVerificationController(
    private val emailVerificationService: EmailVerificationService,
    private val jwtUtil: JwtUtil
) {

    @GetMapping("/domain-check")
    fun checkDomain(@RequestParam(name = "domain") domain : String){

    }



}