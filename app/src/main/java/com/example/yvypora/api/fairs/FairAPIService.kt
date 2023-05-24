package com.example.yvypora.api.fairs
import com.example.yvypora.domain.models.dto.BaseResponseAsPayloadList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FairAPIService {
    @GET("commons/fair/listByClose")
    fun listCloseFairs(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
    ): Call<BaseResponseAsPayloadList<com.example.yvypora.domain.models.Fair>>
}