package com.example.yvypora.models

data class Product (
    val name: String,
    val photo: String,
    val qtdeProduct: Int,
    val price: Float,
    var description: String? = null,
    var promo :Boolean? = null,
    var promo_valor : Int? = null,
    var category : String? = null,

)