package com.example.yvypora.api.fairs

import com.example.yvypora.api.RetrofitApi
import com.example.yvypora.domain.models.Fair
import com.example.yvypora.domain.dto.BaseResponseAsPayloadList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FairsAPIService {
    companion object {
        private val API = RetrofitApi.fairsRetrofitService()

        fun listByClose(
            latitude: String,
            longitude: String,
            onComplete: (BaseResponseAsPayloadList<Fair>) -> Unit
        ) {
            val call = API.listCloseFairs(latitude, longitude)


            call.enqueue(object : Callback<BaseResponseAsPayloadList<Fair>> {
                override fun onFailure(call: Call<BaseResponseAsPayloadList<Fair>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<BaseResponseAsPayloadList<Fair>>,
                    response: Response<BaseResponseAsPayloadList<Fair>>
                ) {
                    val body = response.body()
                    if (response.isSuccessful) {
                        return onComplete.invoke(body!!)
                    }
                }
            })
        }
    }
}