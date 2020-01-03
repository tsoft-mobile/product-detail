package com.tsoftmobile.library.productdetail

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import com.tsoftmobile.library.productdetail.model.ImageSizeType
import java.security.AccessController.getContext


/**
 * User: utku
 * Date: 30.10.2019
 * Time: 09:51
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

object Config {
    var SERVICE_BASE_URL = ""
    var TOKEN = ""
    var imageSizeList = ImageSizeType.medium
    var imageSizeDetail = ImageSizeType.big
    var virguleChar = 2

    private var appMainColor = ""

    private var commonDiscountRateColor = appMainColor

    val commonDiscountBackground: GradientDrawable
        get() {
            val background = GradientDrawable()
            background.setColor(getCommonDiscountRateColor())
            background.cornerRadius = 8f
            background.shape = GradientDrawable.RECTANGLE
            return background
        }

    fun getCommonDiscountRateColor(): Int {
        return Color.parseColor(commonDiscountRateColor)
    }

    fun getAppMainColor(): Int {
        return Color.parseColor(appMainColor)
    }

}