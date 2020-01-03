package com.tsoftmobile.library.productdetail.model.response

import com.tsoftmobile.library.productdetail.model.data.Comment
import com.tsoftmobile.library.productdetail.model.data.Message


/**
 * User: utku
 * Date: 27.11.2019
 * Time: 16:17
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */


data class CommentsResponse(val success:Boolean,val data:ArrayList<Comment>?=null,val message:List<Message>?=null)