package com.example.yvypora.models

import com.example.yvypora.models.dto.Image

data class Fair(
    val name: String,
    val id: Int,
    val review: Int,
    val evaluations: Int,
    val addressId: Int,
    val createdAt: String,
    val updatedAt: String,
    val image: Image,
    val locationId: Int,
    val fairDateHourOfWork: List<FairDateHourOfWork>,
    val marketerCount: Int,
    val latitude: Double,
    val longitude: Double
)

data class FairDateHourOfWork(
    val id: Int,
    val fairId: Int,
    val dateAndHourOfWorkId: Int,
    val createdAt: String,
    val updatedAt: String,
    val dates: FairDates
)

data class FairDates(
    val id: Int,
    val openDatetime: String,
    val closeDatetime: String,
    val createdAt: String,
    val updatedAt: String,
    val dayOfWeekId: Int,
    val dayOfWeek: DayOfWeek,
    val fairDateHourOfWork: List<FairDateHourOfWork>
)

data class DayOfWeek(
    val id: Int,
    val name: String,
    val abbr: String,
    val createdAt: String,
    val updatedAt: String
)

