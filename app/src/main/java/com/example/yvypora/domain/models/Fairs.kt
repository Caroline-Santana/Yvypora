package com.example.yvypora.domain.models

import com.example.yvypora.domain.dto.Image
import com.google.gson.annotations.SerializedName

data class Fair(
    val name: String,
    val id: Int,
    val review: Int,
    val avaliations: Int,
    val addressId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    var image: Image? = null,
    val locationId: Int,
    @SerializedName("fair_date_hour_of_work")
    val fairDateHourOfWork: List<com.example.yvypora.domain.models.FairDateHourOfWork>,
    val marketerCount: Int,
    val latitude: Double,
    val longitude: Double
)

data class FairDateHourOfWork(
    val id: Int,
    val fairId: Int,
    val dateAndHourOfWorkId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val dates: com.example.yvypora.domain.models.FairDates
)

data class FairDates(
    val id: Int,
    @SerializedName("open_datetime")
    val openDatetime: String,
    @SerializedName("close_datetime")
    val closeDatetime: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val dayOfWeekId: Int,
    @SerializedName("day_of_week")
    val dayOfWeek: com.example.yvypora.domain.models.DayOfWeek,
    @SerializedName("fair_date_hour_of_work")
    val fairDateHourOfWork: List<com.example.yvypora.domain.models.FairDateHourOfWork>
)

data class DayOfWeek(
    val id: Int,
    val name: String,
    val abbr: String,
    val createdAt: String,
    val updatedAt: String
)

