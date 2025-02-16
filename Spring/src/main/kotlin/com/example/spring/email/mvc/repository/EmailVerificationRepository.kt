package com.example.spring.email.mvc.repository

import com.example.spring.email.entity.EmailVerification
import org.springframework.data.repository.CrudRepository
import java.util.*

interface EmailVerificationRepository:CrudRepository<EmailVerification,Long>{
    fun findByUserId(userId : UUID);
    fun deleteByUserId(userId : UUID);
}