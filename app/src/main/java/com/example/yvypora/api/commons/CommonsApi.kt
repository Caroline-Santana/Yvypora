package com.example.yvypora.api.commons

import android.content.Context
import android.icu.text.Collator.ReorderCodes
import android.util.Log
import androidx.compose.runtime.collectAsState
import com.example.yvypora.api.RetrofitApi
import com.example.yvypora.models.*
import com.example.yvypora.models.costumer.CostumerInfoError
import com.example.yvypora.models.marketer.Marketer
import com.example.yvypora.service.datastore.TokenStore
import okhttp3.MultipartBody
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


fun createCostumer(costumer: Costumer, onComplete: (CostumerInfoResponse) -> Unit) {
    val call = RetrofitApi.commonsRetrofitService().createCostumer(costumer)
    call.enqueue(object : Callback<CostumerInfoResponse> {
        override fun onResponse(
            call: Call<CostumerInfoResponse>,
            response: Response<CostumerInfoResponse>
        ) {
            val res = response.body()

            Log.i("teste", response.toString())

            if (response.isSuccessful) {
                return onComplete.invoke(res!!)
            }

            Log.i("teste", response.code().toString())
            return onComplete.invoke(CostumerInfoResponse())
        }

        override fun onFailure(call: Call<CostumerInfoResponse>, t: Throwable) {
            t.printStackTrace();
        }


    })
}




fun addPictureToUser(token: String, picture: MultipartBody.Part, onComplete: (String) -> Unit) {
    val _token = "Bearer $token"

    val call = RetrofitApi.commonsRetrofitService().uploadPictureToUser(_token, picture)

    call.enqueue(object: Callback<Any> {
        override fun onResponse(call: Call<Any>, response: Response<Any>) {
            val res = response.body()

            Log.i("teste", response.toString())

            if (response.isSuccessful) {
                return onComplete.invoke(res.toString())
            }

            return onComplete.invoke(res.toString())
        }

        override fun onFailure(call: Call<Any>, t: Throwable) {
            t.printStackTrace()
        }
    })
}
fun fieldsForCostumer(onComplete: (String) -> Unit) {
    val call = RetrofitApi.commonsRetrofitService().fieldsForCostumer()
    call.enqueue(object: Callback<Any> {
        override fun onResponse(call: Call<Any>, response: Response<Any>) {

            val res = response.body()

            if (response.isSuccessful) {
                return onComplete.invoke(res.toString())
            }

            Log.i("teste", response.code().toString())

            return onComplete.invoke(res.toString())
        }

        override fun onFailure(call: Call<Any>, t: Throwable) {
            t.printStackTrace()
        }
    })
}


fun getDetailsOfUser(token: String, onComplete: (User) -> Unit) {
    val _token = "Bearer $token"

    val call = RetrofitApi.commonsRetrofitService().getDetailsOfUser(_token)

    call.enqueue(object: Callback<User> {
        override fun onFailure(call: Call<User>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(call: Call<User>, response: Response<User>) {
            val res = response.body()

            if (response.isSuccessful) {
               return onComplete.invoke(res!!)
            }

            return onComplete.invoke(User())
        }
    })
}


fun createMarketer(marketer: Marketer, onComplete: (String?) -> Unit) {
    val call = RetrofitApi.commonsRetrofitService().createMarketer(marketer)
    call.enqueue(object:  Callback<Any> {
        override fun onResponse(call: Call<Any>, response: Response<Any>) {
            val res = response.body()

            if (response.isSuccessful) {
                return onComplete.invoke(res.toString())
            }

            return onComplete.invoke(null)
        }

        override fun onFailure(call: Call<Any>, t: Throwable) {
            t.printStackTrace()
        }
    })
}







