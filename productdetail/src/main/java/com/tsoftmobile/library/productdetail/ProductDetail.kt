package com.tsoftmobile.library.productdetail

import com.tsoftmobile.library.productdetail.listener.ProductDetailListener
import com.tsoftmobile.library.productdetail.model.RequestType
import com.tsoftmobile.library.productdetail.repository.ProductDetailRepository


/**
 * User: utku
 * Date: 30.10.2019
 * Time: 09:53
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

//class ProductDetail(host: String, token: String) {
//
//    init {
//        Config.SERVICE_BASE_URL = host
//        Config.TOKEN = token
//    }
//
//    private val productDetailRepository = ProductDetailRepository()
//
//    fun getDetail(productId: String, listener: ProductDetailListener) {
//        GlobalScope.launch {
//            val data = productDetailRepository.getProductDetail(productId)
//            if (data.baseOnError) {
//                if (data.baseErrorType == ErrorType.NETWORK) {
//                    listener.onNetworkError(data.baseMessage)
//                } else {
//                    listener.onFailure(data.baseMessage)
//                }
//            } else {
//                if (data.baseData?.data.isNullOrEmpty()) {
//                    listener.onEmpty()
//                } else {
//                    listener.onSuccess(data.baseData?.data)
//                }
//            }
//        }
//    }
//
//    fun getSameProducts(productId: String,requestType: RequestType, listener: ProductDetailListener) {
//        GlobalScope.launch {
//            val data = productDetailRepository.getSameProducts(productId,requestType)
//            if (data.baseOnError) {
//                if (data.baseErrorType == ErrorType.NETWORK) {
//                    listener.onNetworkError(data.baseMessage)
//                } else {
//                    listener.onFailure(data.baseMessage)
//                }
//            } else {
//                if (data.baseData?.data.isNullOrEmpty()) {
//                    listener.onEmpty()
//                } else {
//                    listener.onSuccess(data.baseData?.data)
//                }
//            }
//        }
//    }
//}