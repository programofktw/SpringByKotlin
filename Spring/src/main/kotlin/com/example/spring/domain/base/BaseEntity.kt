package com.example.spring.domain.base

import jakarta.persistence.*
import org.springframework.cglib.core.Local
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long?= null

    @Column
    @CreatedDate
    var createdDate : LocalDateTime? = null

    @Column
    @LastModifiedDate
    var modDate : LocalDateTime?= null
}