package com.example.yvypora.domain.models

data class ProductCardShopping(
    val id: Int,
    val marketerId: Int,
    val name: String,
    val photo: Int,
    val isSelected: Boolean,
    val type_weight: String,
    var weight_product: Int,
    var price: Double,
    var qtde: Int = 1,
)



