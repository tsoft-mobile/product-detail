package com.tsoftmobile.library.productdetail.repository

import com.tsoftmobile.library.productdetail.Config
import com.tsoftmobile.library.productdetail.model.Response
import com.tsoftmobile.library.productdetail.model.response.ProductDetailResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * User: utku
 * Date: 30.10.2019
 * Time: 09:48
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */


class ProductDetailRepository {

    private val TAG = javaClass.simpleName

    fun getProductDetail(productId: String): Observable<Response<ProductDetailResponse>> {
        return Observable.create { emitter ->
            emitter.onNext(Response.loading())

            val params = HashMap<String, Any>()
            params["token"] = Config.TOKEN
            params["fetch_product_detail"] = true
            ApiClient.service.find(productId, params)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    emitter.onNext(Response.success(it))
                    emitter.onComplete()
                }, {
                    emitter.onNext(Response.error(it.message.toString()))
                    emitter.onComplete()
                })
        }
    }


}