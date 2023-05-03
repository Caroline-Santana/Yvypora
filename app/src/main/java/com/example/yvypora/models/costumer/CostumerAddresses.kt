package com.example.yvypora.models.costumer

import com.example.yvypora.models.Address

data class CustomerAddress(
    val id: Int,
    val addressId: Int,
    val costumerId: Int,
    val created_at: String,
    val updated_at: String,
    val address: Address
)
