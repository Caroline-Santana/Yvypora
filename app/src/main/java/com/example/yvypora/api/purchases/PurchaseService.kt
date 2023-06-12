package com.example.yvypora.api.purchases

import android.util.Log
import com.example.yvypora.api.RetrofitApi
import com.example.yvypora.domain.dto.SearchBaseResponse
import com.example.yvypora.domain.dto.StripeIntentResponse
import com.example.yvypora.domain.dto.StripePaymentIntent
import com.example.yvypora.domain.models.product.BaseResponse
import com.example.yvypora.domain.models.product.BaseResponseAsObject
import com.example.yvypora.domain.models.product.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PurchaseService {
    companion object {
        private val API = RetrofitApi.purchaseRetrofitService()

        fun createPurchaseIntent(data: StripePaymentIntent, token: String, onComplete: (StripeIntentResponse) -> Unit) {
            val _token = "Bearer $token"
            val call = API.createStripeIntent(data, _token);

            call.enqueue(object : Callback<StripeIntentResponse> {
                override fun onFailure(call: Call<StripeIntentResponse>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<StripeIntentResponse>,
                    response: Response<StripeIntentResponse>
                ) {
                    val res = response.body()
                    Log.i("stripe", "data stripe -> $data");
                    Log.i("stripe", token.toString());
                    Log.i("stripe", response.toString());

                    if (response.isSuccessful) {
                        return onComplete.invoke(res!!)
                    }
                }
            })
        }

        fun updateCall(onComplete: (Boolean) -> Unit) {
            val call = API.updateCall();

            call.enqueue(object : Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    if (response.isSuccessful) {
                        return onComplete.invoke(true)
                    }
                    return onComplete.invoke(false)
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    t.printStackTrace()
                }
            })

        }
    }
}