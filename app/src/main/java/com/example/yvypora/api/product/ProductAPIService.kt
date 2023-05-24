package com.example.yvypora.api.product

import com.example.yvypora.domain.models.*
import com.example.yvypora.domain.models.dto.SearchBaseResponse
import com.example.yvypora.domain.models.product.BaseResponse
import com.example.yvypora.domain.models.product.BaseResponseAsObject
import com.example.yvypora.domain.models.product.ProductResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface ProductAPIService {
    @GET("costumer/product/")
    fun listAllProducts(
        @Query("category") category: String,
        @Query("score") score: String,
        @Query("lowerPrice") lowerPrice: String,
        @Query("higherPrice") higherPrice: String,
    ): Call<BaseResponse<ProductResponse>>

    @GET("costumer/product/inSaleOff")
    fun atSaleOff(): Call<BaseResponse<ProductResponse>>

    @GET("costumer/product/findNearest")
    fun closeToClient(
        @Header("Authorization") token: String,
    ): Call<BaseResponse<ProductResponse>>


    @GET("costumer/product/{id}")
    fun get(
        @Path("id") id: Int,
    ): Call<BaseResponseAsObject<ProductResponse?>>

    @GET("costumer/search/")
    fun search(
        @Query("q") search: String,
    ): Call<BaseResponseAsObject<SearchBaseResponse<Unit, Unit, ProductResponse>>>
}