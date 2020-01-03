package com.tsoftmobile.library.productdetail.model

import java.io.Serializable


/**
 * User: utku
 * Date: 20.11.2019
 * Time: 09:45
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

enum class ProductFeatureType : Serializable {
    COMMENT_LIST,
    DESCRIPTION,
    INSTALLMENT_LIST,
    TECHNICAL_DESCRIPTION,
    PAYMENT_METHODS,
    ALL_FEATURES
}