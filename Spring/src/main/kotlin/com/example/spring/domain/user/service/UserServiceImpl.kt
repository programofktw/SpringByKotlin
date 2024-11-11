package com.example.spring.domain.user.service


import com.example.spring.domain.user.dto.UserInfoDto
import com.example.spring.domain.user.entity.User
import com.example.spring.domain.user.repository.UserRepository
import com.example.spring.security.jwt.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(

    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil
) : UserService {


    override fun findUserInfo(authorizationHeader: String): UserInfoDto {
        var userId : String = jwtUtil.getUserIdFromToken(authorizationHeader)
        var user : User? = userRepository.findByUserId(UUID.fromString(userId))

        var userInfoDto : UserInfoDto = UserInfoDto(name = user!!.name, providerId = user.providerId, provider = user.provider,uuid = user.userId)

        return userInfoDto
    }
}