package com.example.yvypora.models

data class Address(
 var cep: String = "",
 var number: Int = 0,
 var complemento: String = "",
 var addressTypeId: Number = 1,
 var city: String = "",
 var logradouro: String = "",
 var uf: String = "",
 var neighborhood: String = ""
)