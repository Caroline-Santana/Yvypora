package com.example.yvypora.models

data class Address(
 var cep: String,
 var number: Int,
 var complemento: String,
 var addressTypeId: Number,
 var city: String,
 var logradouro: String,
 var uf: String,
 var neighborhood: String
)