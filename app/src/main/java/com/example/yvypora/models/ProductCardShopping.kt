package com.example.yvypora.models

import com.example.yvypora.ScreenClients.listMarketerCardShopping
import com.example.yvypora.ScreenClients.showPaymentBar
import com.example.yvypora.ScreenClients.total_value

data class ProductCardShopping(
    val id : Int,
    val name : String,
    val photo : Int,
    val isSelected: Boolean,
    val type_weight: String,
    val weight_product : Int,
    val price : Double
)



