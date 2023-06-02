package com.example.yvypora.api.purchases

import com.example.yvypora.domain.models.*
import com.example.yvypora.domain.models.dto.SearchBaseResponse
import com.example.yvypora.domain.models.dto.StripeIntentResponse
import com.example.yvypora.domain.models.dto.StripePaymentIntent
import com.example.yvypora.domain.models.product.BaseResponse
import com.example.yvypora.domain.models.product.BaseResponseAsObject
import com.example.yvypora.domain.models.product.ProductResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface PurchaseAPIService {
    @POST("costumer/purchases/")
    fun createStripeIntent (@Body data: StripePaymentIntent, @Header("Authorization") token: String): Call<StripeIntentResponse>

}