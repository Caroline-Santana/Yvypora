package com.example.yvypora.models

data class Entregador(
    val name: String,
    val photo: String,
    val distancia: Int,
    val medida_comprimento: String,
    var tempo_chegada: Int,
    var score_rating: Int
)
