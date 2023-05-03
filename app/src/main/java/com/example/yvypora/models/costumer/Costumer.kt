package com.example.yvypora.models

data class Costumer (
    val name: String,
    val email: String,
    val password: String,
    val cpf: String,
    val address: Address,
    val birthday: String,
    val gender: String
)