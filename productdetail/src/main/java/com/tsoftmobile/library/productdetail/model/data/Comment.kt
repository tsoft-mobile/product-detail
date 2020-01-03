package com.tsoftmobile.library.productdetail.model.data

import java.util.*


/**
 * User: utku
 * Date: 27.11.2019
 * Time: 16:17
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

data class Comment(
    val commentId: String? = null,
    val productId: String? = null,
    val customerId: String? = null,
    val dateTimeStamp: String? = null,
    val comment: String? = null,
    val rate: Int = 0,
    val isNameDisplayed: String? = null,
    val status: String? = null,
    val customerName: String? = null,
    val date: Date? = null
)