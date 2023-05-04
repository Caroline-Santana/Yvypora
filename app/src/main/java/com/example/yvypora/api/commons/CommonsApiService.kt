package com.example.yvypora.api.commons

import com.example.yvypora.models.Costumer
import com.example.yvypora.models.CostumerInfoResponse
import com.example.yvypora.models.Credentials
import com.example.yvypora.models.Token
import okhttp3.MultipartBody

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part


interface CommonsApiService {
    @POST("commons/login/")
    fun auth(@Body credentials: Credentials): Call<Token>

    @POST("commons/register/costumer")
    fun createCostumer(@Body costumer: Costumer): Call<CostumerInfoResponse>

    @GET("commons/forms/costumer")
    fun fieldsForCostumer(): Call<Any>


    // TODO get details of user
//    @GET("commons/user/details")
//    fun getDetailsOfUser(): Call<User>


    // TODO add JWT TOKEN
    @Multipart
    @PUT("commons/picture/")
    fun uploadPictureToUser(@Header("Authorization") token: String, @Part picture: MultipartBody.Part): Call<Any>

//    @POST("register/marketer")
//    fun createMarketer(@Body marketer: Marketer): Call<Any>
}