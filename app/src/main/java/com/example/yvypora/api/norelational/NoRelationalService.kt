package com.example.yvypora.api.norelational

import android.util.Log
import com.example.yvypora.api.RetrofitApi
import com.example.yvypora.domain.models.MessageFromSocket
import com.example.yvypora.domain.dto.SearchBaseResponse
import com.example.yvypora.domain.models.product.BaseResponse
import com.example.yvypora.domain.models.product.BaseResponseAsObject
import com.example.yvypora.domain.models.product.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoRelationalService {
    companion object {
        private val API = RetrofitApi.noRelationalRetrofitService()

        fun listChat(receiverId: Int, senderId: Int, onComplete: (BaseResponse<MessageFromSocket>) -> Unit) {
            val call = API.listChat(receiverId, senderId)
            call.enqueue(object : Callback<BaseResponse<MessageFromSocket>> {
                override fun onFailure(call: Call<BaseResponse<MessageFromSocket>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<BaseResponse<MessageFromSocket>>,
                    response: Response<BaseResponse<MessageFromSocket>>
                ) {
                    val body = response.body()
                    Log.i("chat", body.toString())
                    if (response.isSuccessful) {
                        return onComplete.invoke(body!!)
                    }
                }
            })
        }

    }
}