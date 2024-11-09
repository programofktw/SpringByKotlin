package com.example.spring.domain.token.refreshtoken.entity

import com.example.spring.domain.base.BaseEntity
import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "refresh_token")
class RefreshToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long?=null,

    @Column(name = "providerId", unique = true)
    var userId : UUID,

    @Column(name = "token", nullable = false)
    var token : String
){
}