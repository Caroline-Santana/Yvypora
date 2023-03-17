package com.example.yvypora.service.constants


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST



interface API {

    @FormUrlEncoded
    @POST("insertUserClient")
    fun submitUser(
        @Field("name")name:String,
        @Field("email")email:String,
        @Field("password")password:String,
        @Field("cpf") cpf:String,
        @Field("cep") cep:String
    ): Call<String>
}