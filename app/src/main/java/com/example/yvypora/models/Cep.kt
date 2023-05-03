package com.example.yvypora.models


import com.google.gson.annotations.SerializedName

data class Cep (
    var cep: String,
    var logradouro: String,
    var complemento: String,
    var bairro: String,
    var localidade: String,
    var uf: String,
)