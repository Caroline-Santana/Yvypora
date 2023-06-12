package com.example.yvypora.api.commons

import android.util.Log
import com.example.yvypora.api.RetrofitApi
import com.example.yvypora.domain.dto.AddAddress
import com.example.yvypora.domain.models.Token
import com.example.yvypora.domain.models.User
import com.example.yvypora.domain.models.marketer.Marketer
import com.example.yvypora.domain.models.*
import com.example.yvypora.domain.models.costumer.Costumer
import com.example.yvypora.domain.dto.CostumerUpdateAccountBody
import com.example.yvypora.domain.dto.CostumerUpdateAccountResponse
import com.example.yvypora.models.CostumerInfoResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun auth(credentials: com.example.yvypora.domain.models.Credentials, onComplete: (Token) -> Unit) {
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
    call.enqueue(object : Callback<Any> {
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
fun updateCostumerAccount(id: Int, body: CostumerUpdateAccountBody, onComplete: (CostumerUpdateAccountResponse?) -> Unit) {
    val call = RetrofitApi.commonsRetrofitService().updateCostumerAccount(id, body)
    call.enqueue(object : Callback<CostumerUpdateAccountResponse> {
        override fun onFailure(call: Call<CostumerUpdateAccountResponse>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(
            call: Call<CostumerUpdateAccountResponse>,
            response: Response<CostumerUpdateAccountResponse>
        ) {
            val body = response.body()

            if (response.isSuccessful) {
                return onComplete.invoke(body!!)
            }

            Log.i("error", response.toString())
            return onComplete.invoke(null)
        }
    })
}

fun addAddress(id: Int, body: AddAddress, onComplete: (Boolean) -> Unit) {
    val call = RetrofitApi.commonsRetrofitService().addAddress(id, body)
    call.enqueue(object : Callback<Any> {
        override fun onFailure(call: Call<Any>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(call: Call<Any>, response: Response<Any>) {
            if (response.isSuccessful) {
                return onComplete.invoke(true)
            }
            Log.i("error", response.toString())

            return onComplete.invoke(false)
        }
    })
}








