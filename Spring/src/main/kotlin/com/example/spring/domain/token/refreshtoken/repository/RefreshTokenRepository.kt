package com.example.spring.domain.token.refreshtoken.repository

import com.example.spring.domain.token.refreshtoken.entity.RefreshToken
import jakarta.transaction.Transactional
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken,Long>{
    fun findByProviderId(providerId : String) : RefreshToken

    @Transactional
    @Modifying
    fun deleteByProviderId(providerId : String)
}