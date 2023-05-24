package com.example.yvypora.api.commons

import com.example.yvypora.domain.models.*
import com.example.yvypora.domain.models.costumer.Costumer
import com.example.yvypora.domain.models.marketer.Marketer
import com.example.yvypora.models.CostumerInfoResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part


interface _CommonsAPIService {
    @POST("commons/login/")
    fun auth(@Body credentials: com.example.yvypora.domain.models.Credentials): Call<Token>

    @POST("commons/register/costumer")
    fun createCostumer(@Body costumer: Costumer): Call<CostumerInfoResponse>

    @POST("commons/register/marketer")
    fun createMarketer(@Body marketer: Marketer): Call<Any>

    @GET("commons/forms/costumer")
    fun fieldsForCostumer(): Call<Any>

    @GET("commons/user/details")
    fun getDetailsOfUser(@Header("Authorization") token: String): Call<User>

    @Multipart
    @PUT("commons/picture/")
    fun uploadPictureToUser(@Header("Authorization") token: String, @Part picture: MultipartBody.Part): Call<Any>
}