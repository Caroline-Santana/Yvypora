package com.example.yvypora.domain.models.costumer

import com.example.yvypora.domain.models.AddressToRegister

data class Costumer(
    val name: String,
    val email: String,
    val password: String,
    val cpf: String,
    val address: AddressToRegister,
    val birthday: String,
    val gender: String
)