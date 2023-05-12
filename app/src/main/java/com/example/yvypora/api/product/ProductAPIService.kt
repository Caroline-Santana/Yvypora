package com.example.yvypora.api.product

import com.example.yvypora.models.*
import com.example.yvypora.models.marketer.Marketer
import com.example.yvypora.models.product.BaseResponse
import com.example.yvypora.models.product.BaseResponseAsObject
import com.example.yvypora.models.product.ProductResponse
import okhttp3.MultipartBody

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query
import retrofit2.http.Path


interface ProductAPIService {
   @GET("costumer/product/")
   fun listAllProducts (
       @Query("category") category: Int,
       @Query("score") score: Int,
       @Query("lowerPrice") lowerPrice: Int,
       @Query("higherPrice") higherPrice: Int,
   ): Call<BaseResponse<ProductResponse>>

   @GET("costumer/product/inSaleOff")
   fun atSaleOff (): Call<BaseResponse<ProductResponse>>

   @GET("costumer/product/findNearest")
   fun closeToClient(
       @Header("Authorization") token: String,
   ): Call<BaseResponse<ProductResponse>>


   @GET("costumer/product/{id}")
   fun get(@Path("id") id: Int): Call<BaseResponseAsObject<ProductResponse>>

}