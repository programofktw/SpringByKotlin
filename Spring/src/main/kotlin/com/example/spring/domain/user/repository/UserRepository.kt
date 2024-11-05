package com.example.spring.domain.user.repository

import com.example.spring.domain.user.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository :CrudRepository<User, Long>{
    fun findByProviderId(providerId : String) : User
}