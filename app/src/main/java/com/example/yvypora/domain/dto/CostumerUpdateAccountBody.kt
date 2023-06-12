package com.example.yvypora.domain.dto

data class CostumerUpdateAccountBody(
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
    var cpf: String? = null
)
