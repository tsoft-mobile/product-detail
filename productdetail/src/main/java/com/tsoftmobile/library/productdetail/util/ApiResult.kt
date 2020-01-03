package com.tsoftmobile.library.productdetail.util


/**
 * User: utku
 * Date: 30.10.2019
 * Time: 16:10
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */


data class ApiResult<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ApiResult<T> {
            return ApiResult(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): ApiResult<T> {
            return ApiResult(Status.ERROR, null, msg)
        }

        fun <T> networkError(msg: String): ApiResult<T> {
            return ApiResult(Status.ERROR, null, msg)
        }

        fun <T> loading(): ApiResult<T> {
            return ApiResult(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
} 