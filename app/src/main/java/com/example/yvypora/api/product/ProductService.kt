package com.example.yvypora.api.product

import android.util.Log
import com.example.yvypora.api.RetrofitApi
import com.example.yvypora.models.product.BaseResponse
import com.example.yvypora.models.product.BaseResponseAsObject
import com.example.yvypora.models.product.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductService {
    companion object {
        private val API = RetrofitApi.productRetrofitService()

        fun listAllProducts(
            category: String,
            score: String,
            lowerPrice: String,
            higherPrice: String,
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

            call.enqueue(object: Callback<BaseResponse<ProductResponse>?> {
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

        fun get(id: Int, onComplete: (BaseResponseAsObject<ProductResponse?>) -> Unit) {
            val call = RetrofitApi.productRetrofitService().get(id)
            call.enqueue(object : Callback<BaseResponseAsObject<ProductResponse?>> {
                override fun onResponse(
                    call: Call<BaseResponseAsObject<ProductResponse?>>,
                    response: Response<BaseResponseAsObject<ProductResponse?>>
                ) {
                    val body = response.body()
                    if (response.isSuccessful) {
                        return onComplete.invoke(body!!)
                    }
                    return onComplete.invoke(BaseResponseAsObject(response.code(), null, true))
                }

                override fun onFailure(
                    call: Call<BaseResponseAsObject<ProductResponse?>>,
                    t: Throwable
                ) {
                    t.printStackTrace()
                }
            })
        }
    }


    fun search(search: String, onComplete: (BaseResponse<ProductResponse>) -> Unit) {
        val call = RetrofitApi.productRetrofitService().search(search)
        call.enqueue(
            object : Callback<BaseResponse<ProductResponse>> {
                override fun onResponse(
                    call: Call<BaseResponse<ProductResponse>>,
                    response: Response<BaseResponse<ProductResponse>>
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<BaseResponse<ProductResponse>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            }
        )
    }

}