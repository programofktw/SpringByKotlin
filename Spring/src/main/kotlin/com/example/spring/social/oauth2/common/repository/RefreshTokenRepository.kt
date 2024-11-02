package com.example.spring.social.oauth2.common.repository

import com.example.spring.social.oauth2.common.entity.token.RefreshToken
import jakarta.transaction.Transactional
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken, Long>{
    @Query("SELECT u FROM RefreshToken u WHERE u.userId = :userId")
    fun findByUserId(userId : UUID)

    @Transactional
    @Modifying
    @Query("DELETE FROM RefreshToken u WHERE u.userId = :userId")
    fun deleteByUserId(userId : UUID);
}