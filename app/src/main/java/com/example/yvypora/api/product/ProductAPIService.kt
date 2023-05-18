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
import retrofit2.http.Path
import retrofit2.http.Query


interface ProductAPIService {
   @GET("costumer/product/")
   fun listAllProducts (
       @Query("category") category: String,
       @Query("score") score: String,
       @Query("lowerPrice") lowerPrice: String,
       @Query("higherPrice") higherPrice: String,
   ): Call<BaseResponse<ProductResponse>>

   @GET("costumer/product/inSaleOff")
   fun atSaleOff (): Call<BaseResponse<ProductResponse>>

   @GET("costumer/product/findNearest")
   fun closeToClient(
       @Header("Authorization") token: String,
   ): Call<BaseResponse<ProductResponse>>


   @GET("costumer/product/{id}")
   fun get(
       @Path("id") id: Int,
   ): Call<BaseResponseAsObject<ProductResponse?>>

   @GET("costumer/search")
   fun search(
       @Query("q") search: String,
   ): Call<BaseResponse<ProductResponse>>
}