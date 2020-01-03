package com.tsoftmobile.library.productdetail.repository

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.tsoftmobile.library.productdetail.BuildConfig
import com.tsoftmobile.library.productdetail.Config
import com.tsoftmobile.library.productdetail.util.JavaNetCookieJar
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit


/**
 * User: utku
 * Date: 30.10.2019
 * Time: 09:48
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */


object ApiClient {
    private var TIMEOUTOFSECOND = 10

    val service: Service by lazy { setupHttpClient() }

    private fun setupHttpClient(): Service {
        val cookieManager = CookieManager()
        CookieHandler.setDefault(cookieManager)
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        val cookieJar =
            JavaNetCookieJar(
                cookieManager
            )

        val client = clientBuilder()
            .connectTimeout(TIMEOUTOFSECOND.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUTOFSECOND.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUTOFSECOND.toLong(), TimeUnit.SECONDS)
            .cookieJar(cookieJar)
            .connectionPool(ConnectionPool())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Config.SERVICE_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(Service::class.java)
    }

    private fun clientBuilder(): OkHttpClient.Builder {
        return if (BuildConfig.DEBUG) {
            OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor())
        } else
            OkHttpClient.Builder()
    }


}
