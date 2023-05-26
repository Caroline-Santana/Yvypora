package com.example.yvypora.views

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.example.yvypora.domain.models.MarketerCardShopping
import com.example.yvypora.domain.models.ProductCardShopping
import com.google.gson.Gson
import kotlin.math.log

class CartViewModel : ViewModel() {
    companion object {
        const val CART_KEY = "CART_KEY"
    }

    fun addCart(product: ProductCardShopping, context: Context, sharedPrefs: SharedPreferences) {
        val _cartList = getCartData(sharedPrefs).toMutableList()

        Log.i("carrinho", _cartList.toString())

        var marketer = _cartList.find {
            it.id_feirante == product.marketerId
        }

        Log.i("carrinho", marketer.toString())


        if (marketer != null) {
            marketer.products += product
            _cartList.forEach {
                if (it.id_feirante == product.marketerId) {
                    _cartList.remove(it)
                    _cartList.add(marketer)
                    Log.i("carrinho", _cartList.toString())
                }
            }
        } else {
            _cartList += MarketerCardShopping(
                    id_feirante = product.marketerId,
                    name = product.marketerName,
                    photo = product.marketerPhoto,
                    sub_name = product.marketerTentName,
                    products = mutableListOf(product)
                )
            Log.i("carrinho", _cartList.toString())
        }

        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)

        with(sharedPrefs.edit()) {
            putString(CART_KEY, Gson().toJson(_cartList))
            apply()
        }

        Log.i("carrinho", _cartList.toString())
    }

    fun removeProductFromCart(
        product: ProductCardShopping,
        context: Context,
        sharedPrefs: SharedPreferences
    ) {
        val _cartList = getCartData(sharedPrefs).toMutableList()

        Log.i("carrinho", _cartList.toString())

        _cartList.map {
            if (it.id_feirante == product.marketerId) {

                Log.i("carrinho", "ACHEI PARA REMOVER ${product.name}")

                it.products.remove(product)
                if (it.products.isEmpty()) {
                    _cartList.remove(it)
                }
            }
        }

        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)

        with(sharedPrefs.edit()) {
            putString(CART_KEY, Gson().toJson(_cartList))
            apply()
        }

    }

    fun changeAmountOfItem(
        newAmount: Int,
        product: ProductCardShopping,
        context: Context,
        sharedPrefs: SharedPreferences
    ) {
        val _cartList = getCartData(sharedPrefs)

        _cartList.map {
            it.products.map { _product ->
                if (_product.id == product.id) {
                    _product.qtde = newAmount
                    if (_product.qtde == 0) {
                        removeProductFromCart(_product, context, sharedPrefs)
                    }
                }
            }
        }

        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)

        with(sharedPrefs.edit()) {
            putString(CART_KEY, Gson().toJson(_cartList))
            apply()
        }

        Log.i("carrinho", _cartList.toString())
    }

    fun getCartData(sharedPrefs: SharedPreferences): List<MarketerCardShopping> {
        try {
            val list: List<MarketerCardShopping> =
                Gson().fromJson(
                    sharedPrefs.getString(CART_KEY, null),
                    Array<MarketerCardShopping>::class.java
                ).toList()

            Log.i("carrinho", list.toString())

            return list
        } catch (e: Exception) {
            Log.i("teste", e.message.toString())

            with(sharedPrefs.edit()) {
                putString(CART_KEY, Gson().toJson(listOf<MarketerCardShopping>()))
                apply()
            }

            val list: List<MarketerCardShopping> =
                Gson().fromJson(
                    sharedPrefs.getString(CART_KEY, null),
                    Array<MarketerCardShopping>::class.java
                ).toList()

            return list
        }
    }
}