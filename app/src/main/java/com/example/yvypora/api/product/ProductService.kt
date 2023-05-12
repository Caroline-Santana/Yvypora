package com.example.yvypora.api.product

import android.content.Context
import android.util.Log
import com.example.yvypora.api.RetrofitApi
import com.example.yvypora.models.Token
import com.example.yvypora.models.product.BaseResponse
import com.example.yvypora.models.product.ProductResponse
import com.example.yvypora.service.datastore.TokenStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ProductService {
    companion object {
        private val API = RetrofitApi.productRetrofitService()

        fun listAllProducts(
            category: Int,
            score: Int,
            lowerPrice: Int,
            higherPrice: Int,
            onComplete: (BaseResponse<ProductResponse>?) -> Unit
        ) {
            val call = API.listAllProducts(category, score, lowerPrice, higherPrice)

            call.enqueue(object : Callback<BaseResponse<ProductResponse>?> {
                override fun onResponse(
                    call: Call<BaseResponse<ProductResponse>?>,
                    response: Response<BaseResponse<ProductResponse>?>
                ) {
                    val body = response.body()

                    Log.i("teste", body.toString())

                    if (response.isSuccessful) {
                        return onComplete.invoke(body)
                    }

                    return onComplete.invoke(null);
                }

                override fun onFailure(call: Call<BaseResponse<ProductResponse>?>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        }

        fun atSaleOff(onComplete: (BaseResponse<ProductResponse>?) -> Unit) {
            val call = API.atSaleOff()

            call.enqueue(object : Callback<BaseResponse<ProductResponse>?> {
                override fun onResponse(
                    call: Call<BaseResponse<ProductResponse>?>,
                    response: Response<BaseResponse<ProductResponse>?>
                ) {
                    val body = response.body()

                    if (response.isSuccessful) {
                        return onComplete.invoke(body)
                    }

                    return onComplete.invoke(null)
                }

                override fun onFailure(call: Call<BaseResponse<ProductResponse>?>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }

        fun closeToClient(token: String, onComplete: (BaseResponse<ProductResponse>?) -> Unit) {
            val call = API.closeToClient(token)

            call.enqueue(object : Callback<BaseResponse<ProductResponse>?> {
                override fun onFailure(call: Call<BaseResponse<ProductResponse>?>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<BaseResponse<ProductResponse>?>,
                    response: Response<BaseResponse<ProductResponse>?>
                ) {
                    val body = response.body()

                    if (response.isSuccessful) {
                        return onComplete.invoke(body)
                    }

                    return onComplete.invoke(null)
                }
            })
        }

        fun get(id: Int, onComplete: (BaseResponse<ProductResponse>?) -> Unit) {
            val call = API.get(id)
            call.enqueue(object : Callback<BaseResponse<ProductResponse>> {
                override fun onFailure(call: Call<BaseResponse<ProductResponse>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<BaseResponse<ProductResponse>>,
                    response: Response<BaseResponse<ProductResponse>>
                ) {
                    val body = response.body()

                    if (response.isSuccessful) {
                        return onComplete.invoke(body)
                    }

                    return onComplete.invoke(null)
                }
            })
        }
    }
}