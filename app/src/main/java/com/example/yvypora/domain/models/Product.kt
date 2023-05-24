package com.example.yvypora.domain.models

data class Product (
    val id: Int,
    val name: String,
    val photo: String,
    val qtdeProduct: Int,
    val price: Float,
    var description: String? = null,
    var promo :Boolean? = null,
    var promo_valor : Int? = null,
    var category : String? = null,
)