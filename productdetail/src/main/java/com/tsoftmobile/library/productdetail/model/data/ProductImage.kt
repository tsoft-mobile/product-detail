package com.tsoftmobile.library.productdetail.model.data

import com.google.gson.annotations.SerializedName
import com.tsoftmobile.library.productdetail.Config
import com.tsoftmobile.library.productdetail.model.ImageSizeType
import java.io.Serializable


/**
 * User: utku
 * Date: 30.10.2019
 * Time: 09:35
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

data class ProductImage(
    @SerializedName("id")
    val id: Int,
    @SerializedName("small")
    val small: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("big")
    val big: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("variant_ids")
    val variantIds: ArrayList<String>,
    @SerializedName("variant_type_id")
    val variantTypeId: Int
):Serializable {

    val defaultImageSizeList: String?
        get() {
            val imageUri: String? = when (Config.imageSizeList) {
                ImageSizeType.small -> small
                ImageSizeType.medium -> medium
                ImageSizeType.big -> big
            }
            return imageUri ?: ""
        }

    val defaultImageSizeDetail: String?
        get() {
            val imageUri: String? = when (Config.imageSizeDetail) {
                ImageSizeType.small -> small
                ImageSizeType.medium -> medium
                ImageSizeType.big -> big
            }
            return imageUri ?: ""
        }
}