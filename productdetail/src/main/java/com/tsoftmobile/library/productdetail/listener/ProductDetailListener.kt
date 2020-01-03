package com.tsoftmobile.library.productdetail.listener

import com.tsoftmobile.library.productdetail.model.data.Product


/**
 * User: utku
 * Date: 30.10.2019
 * Time: 18:18
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

interface ProductDetailListener {
    fun onSuccess(data: ArrayList<Product>?)
    fun onFailure(message: String)
    fun onNetworkError(message: String)
    fun onEmpty()
}