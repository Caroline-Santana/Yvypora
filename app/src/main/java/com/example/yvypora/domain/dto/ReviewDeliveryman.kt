package com.example.yvypora.domain.dto

data class ReviewDeliveryman (
    val review: ReviewDeliverymanDetails

)


data class ReviewDeliverymanDetails (
    val deliverymanId: Int,
    val avaliation: Double,
    )