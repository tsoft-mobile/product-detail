package com.tsoftmobile.library.productdetail.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * User: utku
 * Date: 21.11.2019
 * Time: 10:25
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

data class ProductVariant(
    @SerializedName("variant_id")
    val variantId: String,
    @SerializedName("type_id")
    val typeId: Int,
    @SerializedName("color_code")
    val colorCode: String? = null,
    val type: String,
    @SerializedName("in_stock")
    val inStock: Boolean,
    @SerializedName("type_image")
    val typeImage: String,
    val children: ArrayList<ProductVariant>? = null,
    var selected: Boolean = false,
    var stock: String? = null,
    var price: String? = null
) : Serializable