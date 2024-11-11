package com.example.spring.domain.user.repository

import com.example.spring.domain.user.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository :CrudRepository<User, Long>{
    fun findByUserId(userId : UUID) : User?
    fun findByProviderId(providerId : String) : User?
}