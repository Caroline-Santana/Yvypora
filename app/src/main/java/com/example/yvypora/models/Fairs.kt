package com.example.yvypora.models
import com.google.android.gms.maps.model.LatLng
import com.example.yvypora.models.MarketerFairNear

data class Fairs(
    val latLng: LatLng,
    val title_marker : String,
    val subtitle_marker : String,
    val listMarketer : List<MarketerFairNear>,

    )



