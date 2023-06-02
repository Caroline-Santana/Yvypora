package com.example.yvypora.domain.models

data class EditProfile(
    val name: String?= null,
    val email: String?= null,
    val password: String?= null,
    val cpf: String?= null,
    var cep: String?= null,
)
