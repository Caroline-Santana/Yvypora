package com.example.yvypora.domain.models

import com.google.android.gms.maps.model.LatLng

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
    var coordinates: LatLng

    )



