package com.example.yvypora.domain.models

data class MarketerCardShopping(
    val id_feirante: Int,
    val name: String,
    val sub_name: String,
    val photo: Int,
    var products: List<ProductCardShopping>
)
