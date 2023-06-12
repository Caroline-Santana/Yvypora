package com.example.yvypora.api.fairs
import com.example.yvypora.domain.dto.BaseResponseAsPayloadList
import com.example.yvypora.domain.models.Fair
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FairAPIService {
    @GET("commons/fair/listByClose")
    fun listCloseFairs(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
    ): Call<BaseResponseAsPayloadList<Fair>>
}