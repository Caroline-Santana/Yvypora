package com.example.yvypora.models

import com.example.yvypora.models.costumer.CustomerAddress
import com.example.yvypora.models.dto.Gender

data class CustomerInfoResponse(
    val gender: Gender,
    val name: String,
    val email: String,
    val costumer_addresses: List<CustomerAddress>
)




