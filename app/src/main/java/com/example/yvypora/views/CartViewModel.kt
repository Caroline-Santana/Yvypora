package com.example.yvypora.views

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.yvypora.domain.models.MarketerCardShopping
import com.example.yvypora.domain.models.Product
import com.example.yvypora.domain.models.ProductCardShopping

class CartViewModel : ViewModel() {
    var listMarketerCardShopping = mutableStateListOf<MarketerCardShopping>();

    fun addCart(product: ProductCardShopping) {
        var newList = mutableStateListOf<MarketerCardShopping>();
        val ( marketerId ) = product;
        var marketer = listMarketerCardShopping.find {
            it.id_feirante === marketerId
        }
        if (marketer != null) {
            marketer.products += product
            listMarketerCardShopping.forEach {
                if (it.id_feirante == marketerId) {
                    newList.add(marketer)
                } else {
                    newList.add(it)
                }
            }
        } else {
//            listMarketerCardShopping.add(
//                MarketerCardShopping() // TODO
//            )
        }

        listMarketerCardShopping = newList
    }

    fun removeProductFromCart(product: ProductCardShopping) {
        listMarketerCardShopping.map {
            if (it.id_feirante == product.marketerId) {
                it.products.toMutableList().remove(product)
                if (it.products.isEmpty()) {
                    listMarketerCardShopping.remove(it)
                }
            }
        }
    }

    fun changeAmountOfItem(newAmount: Int, product: ProductCardShopping) {
        listMarketerCardShopping.map {
            it.products.map {_product ->
                if (_product.id == product.id) {
                    _product.qtde = newAmount
                    if (_product.qtde == 0) {
                        removeProductFromCart(_product)
                    }
                }
            }
        }
    }
}