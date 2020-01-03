package com.tsoftmobile.library.productdetail.repository

import com.tsoftmobile.library.productdetail.model.response.ProductDetailResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * User: utku
 * Date: 30.10.2019
 * Time: 11:37
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

interface Service {

    @FormUrlEncoded
    @POST("product/find/{product}")
    fun find(@Path("product") productId: String, @FieldMap params: HashMap<String, Any>): Single<ProductDetailResponse>


}