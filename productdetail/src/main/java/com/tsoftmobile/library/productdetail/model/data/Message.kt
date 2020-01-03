package com.tsoftmobile.library.productdetail.model.data


/**
 * User: utku
 * Date: 30.10.2019
 * Time: 09:47
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

data class Message(
    val type: Int? = null,
    val code: String? = null,
    val index: Int? = null,
    val text: ArrayList<String>? = null,
    val errorField: ArrayList<String>? = null
)