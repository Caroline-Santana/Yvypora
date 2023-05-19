package com.example.yvypora.models

import com.example.yvypora.models.dto.Image

data class FairsMap(
    val id: Int,
    val photo: String,
    val name: String,
    val dayOfWork: String,
    val hourStartOfWork: Int,
    val minuteStartOfWork: Int,
    val hourEndOfWork: Int,
    val minuteEndOfWork: Int,
    val aproxUserCloser: Int,
    val ratingMarketer: Double,
    )



