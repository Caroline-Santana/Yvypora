package com.example.yvypora.models

data class AddressCard(
    var titulo: String,
    var name_remetente: String,
    var email_remetente: String,
    var rua: String,
    var numero: Int,
    var cidade: String,
    var estado: String,
    var pais: String,
    var endereço_principal: Boolean,
)
