package com.example.spring.domain.user.entity

import com.example.spring.domain.base.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "users")
class User (
    @Column(columnDefinition = "BINARY(16)", unique = true)
    var uuid : UUID,

    @Column
    var name : String,

    @Column
    var provider : String,

    @Column
    var providerId : String,

    ): BaseEntity(){
}