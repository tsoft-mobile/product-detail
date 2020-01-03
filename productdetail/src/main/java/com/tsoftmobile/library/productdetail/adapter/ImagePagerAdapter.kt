package com.tsoftmobile.library.productdetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.tsoftmobile.library.productdetail.R
import com.tsoftmobile.library.productdetail.model.ImageTypeByPlace
import com.tsoftmobile.library.productdetail.model.data.ProductImage
import com.tsoftmobile.library.productdetail.util.loadUrl
import java.util.ArrayList


/**
 * User: utku
 * Date: 21.11.2019
 * Time: 15:28
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */


class ImagePagerAdapter(
    private var imageList: ArrayList<ProductImage>?,
    private val placeType: ImageTypeByPlace
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(container.context)
        val imageView =
            layoutInflater.inflate(R.layout.simple_image_view, container, false) as ImageView
        val imageUrl = imageList?.get(position)?.let {
            if (placeType == ImageTypeByPlace.Detail) {
                it.defaultImageSizeDetail
            } else {
                it.defaultImageSizeList
            }
        }
        imageView.loadUrl(imageUrl ?: "")
        container.addView(imageView)

        return imageView
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun getCount(): Int {
        return imageList?.size ?: 0
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    fun replaceData(imageList: ArrayList<ProductImage>) {
        this.imageList = imageList
        notifyDataSetChanged()
    }

}
