package com.example.yvypora.models.marketer

import com.example.yvypora.models.dto.Location

data class Marketer (
    val name: String,
    val email: String,
    val password: String,
    val gender: String,
    val phone: String,
    val tent_name: String,
    val location: Location,
    val birthday: String,
    val cpf: String
)