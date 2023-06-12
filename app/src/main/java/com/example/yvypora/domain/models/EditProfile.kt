package com.example.yvypora.domain.models

data class EditProfile(
    var name: String?= null,
    var email: String?= null,
    var password: String?= null,
    var cpf: String?= null,
    var cep: String?= null,
)