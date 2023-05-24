package com.example.yvypora.domain.models


data class MarketerCard(
    val name: String,
    val sub_name: String,
    val photo: Int,
    val date: String,
    val products: List<ProductCardSale>
)



