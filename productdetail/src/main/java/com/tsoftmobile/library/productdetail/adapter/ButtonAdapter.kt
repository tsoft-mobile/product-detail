package com.tsoftmobile.library.productdetail.adapter

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tsoftmobile.library.productdetail.R
import com.tsoftmobile.library.productdetail.model.data.LayoutButton
import com.tsoftmobile.library.productdetail.util.dp
import com.tsoftmobile.library.productdetail.util.screenWidth

class ButtonAdapter(private val items: ArrayList<LayoutButton>) :
    BaseQuickAdapter<LayoutButton, BaseViewHolder>(
        R.layout.item_button, items
    ) {
    override fun convert(helper: BaseViewHolder, item: LayoutButton) {
        val rootLayout = helper.getView<LinearLayout>(R.id.layout_root)
//        rootLayout.layoutParams.width = mContext.screenWidth() / items.size - 8.dp
//        rootLayout.requestLayout()

        if (recyclerView != null) {
            rootLayout.layoutParams.width = recyclerView.measuredWidth / items.size
            rootLayout.requestLayout()
        }

        val imageView = helper.getView<ImageView>(R.id.image)
        helper.setText(R.id.text, item.text)
        if (item.drawableImage != null) {
            Glide.with(imageView).load(item.drawableImage).into(imageView)
        } else {
            Glide.with(imageView).load(item.image).into(imageView)
        }
    }
}