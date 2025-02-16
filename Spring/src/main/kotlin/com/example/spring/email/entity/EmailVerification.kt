package com.example.spring.email.entity

import jakarta.persistence.*
import java.util.*

@Entity
data class EmailVerification(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,

    @Column(name = "user_id")
    val userId : UUID,

    @Column(name = "code")
    val code : String
)
