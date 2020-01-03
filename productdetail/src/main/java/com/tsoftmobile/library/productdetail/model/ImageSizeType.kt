package com.tsoftmobile.library.productdetail.model


/**
 * User: utku
 * Date: 14.11.2019
 * Time: 16:12
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

enum class ImageSizeType {
    small, medium, big;

    companion object {
        fun getTypeByValue(value: String): ImageSizeType {
            when (value) {
                "B" -> return big
                "O" -> return medium
                "K" -> return small
                else -> return medium
            }
        }
    }
}