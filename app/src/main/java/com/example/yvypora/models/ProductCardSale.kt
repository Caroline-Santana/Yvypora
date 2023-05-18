package com.example.yvypora.models

data class ProductCardSale(
    val id : Int,
    val name : String,
    val photo : String,
    val qntd_product : Int,
    val type_weight: String,
    val weight_product : Int,
    val price : Double,
    val promo : Boolean,
)
