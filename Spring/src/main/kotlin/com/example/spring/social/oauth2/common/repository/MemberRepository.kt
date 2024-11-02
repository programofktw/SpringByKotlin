package com.example.spring.social.oauth2.common.repository

import org.springframework.data.repository.CrudRepository
import com.example.spring.social.oauth2.common.entity.Member
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MemberRepository : CrudRepository<Member, Long> {
    @Query("SELECT u FROM Member u WHERE u.userId = :userId")
    fun findByUserId(userId: UUID): Optional<Member>

    fun findByProviderId(providerId: String): Member?
}