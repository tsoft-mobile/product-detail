package com.tsoftmobile.library.productdetail.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tsoftmobile.library.productdetail.R
import com.tsoftmobile.library.productdetail.model.data.Feature

class FeatureAdapter(private val items: ArrayList<Feature>) :
    BaseQuickAdapter<Feature, BaseViewHolder>(
        R.layout.item_feature
    ) {

    override fun convert(helper: BaseViewHolder, item: Feature) {
        helper.setText(R.id.text, item.title)
    }

}