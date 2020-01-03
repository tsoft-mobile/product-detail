package com.tsoftmobile.library.productdetail.model.data

import com.tsoftmobile.library.productdetail.model.LayoutButtonType

data class LayoutButton(
    var type: LayoutButtonType,
    var text: String,
    var image: String? = null,
    var drawableImage: Int? = null
)