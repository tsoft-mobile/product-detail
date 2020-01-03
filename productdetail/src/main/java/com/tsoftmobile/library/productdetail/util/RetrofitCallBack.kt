package com.tsoftmobile.library.productdetail.util

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * User: utku
 * Date: 30.10.2019
 * Time: 17:46
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

abstract class RetrofitCallBack<T>() : Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {

    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onNetworkError(t.message ?: "")
    }

    fun onSuccess(data: T) {

    }

    fun onError(message: String) {

    }

    fun onNetworkError(message: String) {

    }
}