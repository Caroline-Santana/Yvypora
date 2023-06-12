package com.example.yvypora.domain.dto

data class ReviewProduct (
    val reviews: List<ReviewProductDetails>,
)

data class ReviewProductDetails(
    val productId: Int,
    val avaliation: Double,
)
