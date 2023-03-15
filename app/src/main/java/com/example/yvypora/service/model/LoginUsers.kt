package com.example.yvypora.service.model

data class LoginUsers(
    val login: String,
    val password: String
)

data class RetornoApi (
    val message: String,
    val token: String,
    val id: Int
)