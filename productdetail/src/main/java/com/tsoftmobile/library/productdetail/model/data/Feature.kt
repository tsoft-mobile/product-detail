package com.tsoftmobile.library.productdetail.model.data

import com.tsoftmobile.library.productdetail.model.ProductFeatureType
import java.io.Serializable


/**
 * User: utku
 * Date: 20.11.2019
 * Time: 15:04
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

data class Feature(
    val title: String = "",
    val type: ProductFeatureType,
    var selected: Boolean = false
) : Serializable