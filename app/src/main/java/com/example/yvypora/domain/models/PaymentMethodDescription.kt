package com.example.yvypora.domain.models

data class PaymentMethodDescription(
    val id: Int,
    var name_method: String,
    var description_method: String,
    var logo : Int,
    var isSelected: Boolean,
    val card: List<com.example.yvypora.domain.models.CardPayment> ?= null
)