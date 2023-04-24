package com.example.yvypora.models

data class ProductCardShopping(
    val id : Int,
    val name : String,
    val photo : Int,
    val isSelected: Boolean,
    val type_weight: String,
    val weight_product : Int,
    val price : Double
)
