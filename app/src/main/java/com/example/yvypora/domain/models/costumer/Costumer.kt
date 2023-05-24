package com.example.yvypora.domain.models.costumer
data class Costumer(
    val name: String,
    val email: String,
    val password: String,
    val cpf: String,
    val address: com.example.yvypora.domain.models.Address,
    val birthday: String,
    val gender: String
)