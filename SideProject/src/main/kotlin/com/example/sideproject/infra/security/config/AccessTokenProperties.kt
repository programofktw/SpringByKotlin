package com.example.sideproject.infra.security.config

class AccessTokenProperties {

    lateinit var secret : String

    var expirationTime : Long = 0L
}