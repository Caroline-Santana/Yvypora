package com.example.yvypora.service.model

import com.google.gson.annotations.SerializedName

data class Cep(
    var cep: String,
    var logradouro: String,
    var complemento: String,
    var bairro: String,
    @SerializedName("cidade") var localidade: String,
    @SerializedName("estado") var uf: String,
)
