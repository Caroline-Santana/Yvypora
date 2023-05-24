package com.example.yvypora.domain.models

data class CardPayment(
    var nome_titular: String,
    var numero_cartao: String,
    var cvv: Int,
    var data_validade: String,
    var type_card : String
)
