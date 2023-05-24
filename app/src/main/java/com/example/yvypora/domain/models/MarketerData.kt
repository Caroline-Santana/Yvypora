package com.example.yvypora.domain.models

data class MarketerData(
    var name : String? = null,
    var photo : String? = null,
    var products : List<ProductCardSale>? = null
)
