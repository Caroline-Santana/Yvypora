package com.example.yvypora.models.product

import com.example.yvypora.models.dto.Image

data class ImageOfProduct(
    val id: Int,
    val imageId: Int,
    val productId: Int,
    val createdAt: String,
    val updatedAt: String,
    val image: Image
)