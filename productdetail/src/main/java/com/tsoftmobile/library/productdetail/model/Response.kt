package com.tsoftmobile.library.productdetail.model


/**
 * User: utku
 * Date: 20.11.2019
 * Time: 16:54
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

data class Response<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Response<T> {
            return Response(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): Response<T> {
            return Response(Status.ERROR, null, msg)
        }

        fun <T> loading(): Response<T> {
            return Response(Status.LOADING, null, null)
        }

        fun <T> empty(): Response<T> {
            return Response(Status.EMPTY, null, null)
        }
    }
}