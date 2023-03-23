package com.example.yvypora.models
data class Costumer (
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var gender: Char = 'M',
    var address: Address,
    var birthday: String,
    var cpf: String
)


