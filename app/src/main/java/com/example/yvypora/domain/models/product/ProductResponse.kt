package com.example.yvypora.domain.models.product

import com.example.yvypora.domain.dto.CountData
import com.example.yvypora.domain.dto.SaleOff
import com.example.yvypora.domain.dto.TypeOfPrice
import com.example.yvypora.domain.models.marketer.Marketer
import com.example.yvypora.domain.models.product.ImageOfProduct
import com.google.gson.annotations.SerializedName


data class ProductResponse (
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int?,
    val review: Float,
    val avaliations: Int,
    @SerializedName("active_for_selling")
    val activeForSelling: Boolean,
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    val marketerId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val description: String,
    val categoryOfProductId: Int,
    val typeOfProductId: Int,
    @SerializedName("sale_off")
    var saleOff: List<SaleOff>? = null,
    @SerializedName("type_of_price")
    val typeOfPrice: TypeOfPrice,
    @SerializedName("image_of_product")
    val imageOfProduct: List<ImageOfProduct>,
    val marketer: Marketer,
    var latitude: Float? = null,
    var longitude: Float? = null,
    val _count: CountData? = null,
)

