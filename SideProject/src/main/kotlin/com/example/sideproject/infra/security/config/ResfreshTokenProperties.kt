package com.example.sideproject.infra.security.config

class ResfreshTokenProperties {
    lateinit var secret : String

    var expirationTime : Long = 0L
}