package com.example.yvypora.domain.models.dto

data class Location(
    var id: Int? = null,
    var longitude: Double?,
    var latitude: Double?,
    var created_at: String? = null,
    var updated_at: String? = null
)