package com.example.sideproject.jwt

import com.example.sideproject.infra.security.config.JwtProperties
import com.example.sideproject.infra.security.jwt.JwtUtil
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class JwtUtilImplTest @Autowired constructor(
    private val jwtUtil: JwtUtil,
    private val jwtProperties: JwtProperties
) {

    private val testUserId: UUID = UUID.randomUUID()

    @Test
    fun `AccessToken 사용처에 AccessToken 사용 - 기대값 성공`() {
        val accessToken = jwtUtil.generateAccessToken(testUserId)
        val userId = jwtUtil.getUserIdFromToken(accessToken, isAccess = true)
        assertEquals(testUserId.toString(), userId)
    }

    @Test
    fun `RefreshToken 사용처에 RefreshToken 사용 - 기대값 성공`() {
        val refreshToken = jwtUtil.generateRefreshToken(testUserId)
        val userId = jwtUtil.getUserIdFromToken(refreshToken, isAccess = false)
        assertEquals(testUserId.toString(), userId)
    }

    @Test
    fun `RefreshToken 사용처에 AccessToken 사용 - 기대값 실패`() {
        val accessToken = jwtUtil.generateAccessToken(testUserId)
        assertThatThrownBy {
            jwtUtil.getUserIdFromToken(accessToken, isAccess = false)
        }.isInstanceOf(Exception::class.java) // TODO: CustomException으로 변경 시 그걸로
    }

    @Test
    fun `AccessToken 사용처에 RefreshToken 사용 - 기대값 실패 `() {
        val refreshToken = jwtUtil.generateRefreshToken(testUserId)
        assertThatThrownBy {
            jwtUtil.getUserIdFromToken(refreshToken, isAccess = true)
        }.isInstanceOf(Exception::class.java)
    }

    @Test
    fun `AccessToken 사용처에 AccessToken을 사용하 - 기대값을 실패할경우 - 테스트 결과는 실패해야함`() {
        val accessToken = jwtUtil.generateAccessToken(testUserId)
        assertThatThrownBy {
            jwtUtil.getUserIdFromToken(accessToken, isAccess = true)
        }.isInstanceOf(Exception::class.java) // TODO: CustomException으로 변경 시 그걸로
    }
}