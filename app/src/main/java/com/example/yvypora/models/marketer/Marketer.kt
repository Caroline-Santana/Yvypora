package com.example.yvypora.models.marketer

import com.example.yvypora.models.dto.Location

data class Marketer (
    var id: Int? = -1,
    val name: String,
    val email: String,
    val password: String,
    val gender: String,
    val phone: String,
    val online: Boolean,
    var cnpj: String? = null,
    val review: Float,
    val avaliations: Float,
    val tent_name: String,
    val location: Location,
    val birthday: String,
    val cpf: String
)
