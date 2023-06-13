package com.example.yvypora.domain.models

import com.example.yvypora.domain.dto.SaleOff

data class ProductCardShopping (
    val id: Int,
    val marketerId: Int,
    val marketerName: String,
    val marketerPhoto: String,
    val marketerTentName: String,
    val name: String,
    val photo: String,
    val isSelected: Boolean,
    val type_weight: String,
    var weight_product: Int,
    var price: Double,
    var qtde: Int = 1,
    var saleOff: List<SaleOff>? = null,
)



