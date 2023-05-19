package com.example.yvypora.api.fairs
import com.example.yvypora.models.Fair
import com.example.yvypora.models.dto.BaseResponseAsPayloadList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FairAPIService {
    @GET("commons/fair/listByClose")
    fun listCloseFairs(
        @Query("latitude") latitude: Float,
        @Query("longitude") longitude: Float,
    ): Call<BaseResponseAsPayloadList<Fair>>
}