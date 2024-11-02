
package com.example.spring.social.oauth2.common.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "users")
class Member(

    @Column(name = "users_uuid", columnDefinition = "BINARY(16)", unique = true)
    val userId: UUID,

    @Column(name = "name", nullable = false, length = 5)
    val name: String,

    @Column(name = "provider", nullable = false, length = 10)
    val provider: String,

    @Column(name = "provider_id", nullable = false, length = 50)
    val providerId: String,

    ):BaseEntity(){

}
