package com.example.spring.domain.user.service


import com.example.spring.domain.user.dto.UserInfoDto
import com.example.spring.domain.user.entity.User
import com.example.spring.domain.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl() : UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    override fun findUserInfo(authorizationHeader: String): UserInfoDto {
        var providerId = ""

        var user : User = userRepository.findByProviderId(providerId)

        var userInfoDto : UserInfoDto = UserInfoDto(name = user.name, providerId = user.providerId, provider = user.provider)

        return userInfoDto
    }
}