package com.example.spring

import com.example.spring.security.jwt.JwtUtil
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ApplicationTests {

    @Autowired
    lateinit var jwtUtil : JwtUtil
    @Test
    fun contextLoads() {
        System.out.println(jwtUtil.SECRET_KEY);
    }


}
