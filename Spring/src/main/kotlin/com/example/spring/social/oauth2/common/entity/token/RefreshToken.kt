package com.example.spring.social.oauth2.common.entity.token

import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "refresh_tokens")
class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_tokens_id")
    var id :Long? = null

    @Column(name = "users_uuid", columnDefinition = "BINARY(16)", unique = true)
    var userId : UUID?= null

    @Column(name = "token", nullable = false)
    var token : String = ""
}