package com.example.yvypora.domain.models.dto

data class StripePaymentIntent(
    val costumer_address_id: Int,
    val products: List<ProductToStripe>,
    val freight: Double
)


data class ProductToStripe(
    val id: Int,
    val amount: Int
)
