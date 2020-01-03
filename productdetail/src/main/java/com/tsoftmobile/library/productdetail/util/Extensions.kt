package com.tsoftmobile.library.productdetail.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.core.content.ContextCompat.getSystemService
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


/**
 * User: utku
 * Date: 14.11.2019
 * Time: 16:17
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

fun Context.screenWidth(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = wm.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.x
}

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f)


fun <T> ArrayList<T>.cloneArray(): ArrayList<T> {
    val arrayList = arrayListOf<T>()
    this.forEach {
        arrayList.add(it)
    }
    return arrayList
}

fun View.setOnSingleClickListener(listener: (view: View) -> Unit): View {
    this.setOnClickListener {
        listener.invoke(this)
        this.isEnabled = false
        Handler().postDelayed({
            this.isEnabled = true
        }, 100)
    }
    return this
}

fun View.visible(): View {
    this.visibility = View.VISIBLE
    return this
}

fun View.invisible(): View {
    this.visibility = View.INVISIBLE
    return this
}

fun View.gone(): View {
    this.visibility = View.GONE
    return this
}

fun ImageView.loadUrl(url: String) {
    val mUrl = url.encodeTurkishCharactersInUrl()
    Glide.with(this.context)
        .load(mUrl)
        .fitCenter()
        .into(this)
}


fun String.encodeTurkishCharactersInUrl(): String {
    var url = this
    val list = arrayOf("ü", "ç", "ı", "ö", "ğ", "ş", " ", "Ü", "Ç", "İ", "Ö", "Ğ", "Ş")
    for (i in list.indices) {
        try {
            url = url.replace(list[i], URLEncoder.encode(list[i], "UTF-8").replace("+", "%20"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

    }
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
        url = url.replace("https", "http")
    }
    return url
}