package com.example.yvypora.domain.dto

data class AddAddress(
    val addressTypeId: Int, // Apartment or House (0, 1)
    val cep: String,
    val uf: String,
    val city: String,
    val neighborhood: String,
    val number: Int,
)
