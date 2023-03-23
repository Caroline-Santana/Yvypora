package com.example.yvypora.api.commons

import android.util.Log
import com.example.yvypora.api.RetrofitApi
import com.example.yvypora.models.Costumer
import com.example.yvypora.models.Credentials
import com.example.yvypora.models.Token
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun auth(credentials: Credentials, onComplete: (Token) -> Unit) {
    val call = RetrofitApi.commonsRetrofitService().auth(credentials)

    call.enqueue(object : Callback<Token> {
        override fun onResponse(call: Call<Token>, response: Response<Token>) {
            val res = response.body()

            Log.i("teste", res.toString())

            if (res?.error == true) onComplete.invoke(Token())

            try {
                onComplete.invoke(res!!)
            } catch (error: NullPointerException) {
                onComplete.invoke(Token())
            }
        }

        override fun onFailure(call: Call<Token>, t: Throwable) {

        }
    })
}


fun createCostumer(costumer: Costumer, onComplete: (Any) -> Unit) {
    val call = RetrofitApi.commonsRetrofitService().createCostumer(costumer)
    Log.i("teste", costumer.toString())
    call.enqueue(object : Callback<Any> {
        override fun onResponse(
            call: Call<Any>,
            response: Response<Any>
        ) {


            if (response.isSuccessful) {
                return onComplete.invoke(true)
            }

            Log.i("teste", response.code().toString())
            return onComplete.invoke(false)
        }

        override fun onFailure(call: Call<Any>, t: Throwable) {
            t.printStackTrace();
        }


    })
}

