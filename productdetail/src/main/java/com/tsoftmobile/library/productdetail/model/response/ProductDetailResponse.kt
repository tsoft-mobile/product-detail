package com.tsoftmobile.library.productdetail.model.response

import com.tsoftmobile.library.productdetail.model.data.Message
import com.tsoftmobile.library.productdetail.model.data.Product


/**
 * User: utku
 * Date: 30.10.2019
 * Time: 09:46
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

data class ProductDetailResponse(
    val success: Boolean,
    val data: ArrayList<Product>?=null,
    val message: ArrayList<Message>? = null
)