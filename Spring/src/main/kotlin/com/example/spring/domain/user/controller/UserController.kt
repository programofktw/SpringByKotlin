package com.example.spring.domain.user.controller

import com.example.spring.domain.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/userInfo")
    fun getUserInfo(@RequestHeader("Authorization") authorizationHeader:String) :Map<String, Any?>{
        var result : MutableMap<String, Any> = mutableMapOf()
        result.put("userInfo", userService.findUserInfo(authorizationHeader))
        return result
    }

    @GetMapping("/test")
    fun getTest(): Map<String, Any>{
        var result : MutableMap<String, Any> = mutableMapOf()
        result.put("uses", "1234")

        return result
    }
}