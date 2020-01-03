package com.tsoftmobile.library.productdetail.adapter

import android.graphics.Color
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tsoftmobile.library.productdetail.R
import com.tsoftmobile.library.productdetail.model.data.Product
import com.tsoftmobile.library.productdetail.model.data.ProductVariant
import com.tsoftmobile.library.productdetail.util.dp
import com.tsoftmobile.library.productdetail.util.gone
import com.tsoftmobile.library.productdetail.util.visible


/**
 * User: utku
 * Date: 21.11.2019
 * Time: 11:05
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

class ProductVariantAdapter(items: ArrayList<ProductVariant>, private val product: Product) :
    BaseQuickAdapter<ProductVariant, BaseViewHolder>(
        R.layout.item_variant, items
    ) {
    override fun convert(helper: BaseViewHolder, item: ProductVariant) {
        val imageView = helper.getView<ImageView>(R.id.image)
        val container = helper.getView<LinearLayout>(R.id.container)

        if (item.inStock) {
            helper.itemView.isEnabled = true
            helper.getView<ImageView>(R.id.no_stock).gone()
        } else {
            helper.itemView.isEnabled = false
            helper.getView<ImageView>(R.id.no_stock).visible()
        }

        if (item.selected) {
//            helper.setBackgroundColor(R.id.container, Color.parseColor(item.colorCode))
            container
                .setBackgroundResource(R.drawable.shape_rounded_bordered_background_primary)
            helper.getView<TextView>(R.id.image_name)
                .setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
        } else {
            container
                .setBackgroundResource(R.drawable.shape_rounded_bordered_background_grey)

            helper.getView<TextView>(R.id.image_name)
                .setTextColor(ContextCompat.getColor(mContext, R.color.md_grey_700))
        }

        product.imageList.forEach { productImage ->
            if (productImage.variantIds.isEmpty()) {
                productImage.variantIds.add("-1")
            }
            productImage.variantIds.forEach {
                if (it == item.variantId) {
                    if (item.colorCode.isNullOrEmpty()) {
                        helper.getView<FrameLayout>(R.id.layout_view_color).gone()
                    } else {
                        helper.getView<FrameLayout>(R.id.layout_view_color).visible()
                        try {
                            helper.setBackgroundColor(
                                R.id.view_color,
                                Color.parseColor(item.colorCode)
                            )
                        } catch (e: Exception) {
                        }
                    }
                    imageView.visible()
                    helper.getView<FrameLayout>(R.id.layout_view_color).visible()
                    Glide.with(imageView).load(productImage.medium).into(imageView)
                    val lp = container.layoutParams
                    lp.width = 150.dp
                    lp.height = 150.dp
                    container.layoutParams = lp
                } else {
                    imageView.gone()
                    helper.getView<FrameLayout>(R.id.layout_view_color).gone()
                    val lp =
                        container.layoutParams as RecyclerView.LayoutParams
                    lp.width = RecyclerView.LayoutParams.WRAP_CONTENT
                    lp.height = RecyclerView.LayoutParams.WRAP_CONTENT
                    container.layoutParams = lp
                }
            }
        }

        helper.setText(R.id.image_name, item.type)
    }

}