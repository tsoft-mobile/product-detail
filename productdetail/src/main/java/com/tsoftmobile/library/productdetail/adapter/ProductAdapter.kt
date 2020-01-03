package com.tsoftmobile.library.productdetail.adapter
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tsoftmobile.library.productdetail.Config
import com.tsoftmobile.library.productdetail.R
import com.tsoftmobile.library.productdetail.databinding.ItemProductBinding
import com.tsoftmobile.library.productdetail.model.data.Product
import com.tsoftmobile.library.productdetail.util.encodeTurkishCharactersInUrl
import com.tsoftmobile.library.productdetail.util.screenWidth
import java.util.*


/**
 * User: utku
 * Date: 26.11.2019
 * Time: 10:35
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

class ProductAdapter(val items: List<Product>, @LayoutRes val layout: Int = R.layout.item_product) :
    BaseQuickAdapter<Product, ProductAdapter.CustomViewHolder>(layout, items) {

    override fun convert(helper: CustomViewHolder, item: Product) {


        helper?.binding?.title?.text = item?.title

        val priceSellText = (String.format(
            Locale("tr", "TR"),
            "%,." + Config.virguleChar + "f",
            item?.priceSell
        ) + " " + item?.targetCurrency) + " " + mContext.getString(R.string.vat)

        helper?.binding?.priceSell?.text = priceSellText


        val imageUrl = item?.image?.defaultImageSizeList?.encodeTurkishCharactersInUrl() ?: ""

        helper?.binding?.photo?.let {
            Glide.with(it).load(imageUrl)
                .into(it)
        }

    }

    inner class CustomViewHolder(v: View) :
        BaseViewHolder(v) {  // her bir item'ın genişliğini ayarla.
        var binding = DataBindingUtil.bind<ItemProductBinding>(v)
        init {
            //Renk Ayarlamaları
            binding?.discountPercent?.background =
                Config.commonDiscountBackground
            binding?.priceSell?.setTextColor(Config.getAppMainColor())
            val params = binding?.productContainer?.layoutParams
            params?.width = (mContext.screenWidth() / (2.2f)).toInt()
            binding?.productContainer?.layoutParams = params
        }
    }
}