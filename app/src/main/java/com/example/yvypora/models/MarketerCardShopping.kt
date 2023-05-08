package com.example.yvypora.models

data class MarketerCardShopping(
    val id_feirante: Int,
    val name: String,
    val sub_name: String,
    val photo: Int,
    val products: List<ProductCardShopping>
)
