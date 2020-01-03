package com.tsoftmobile.library.productdetail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.tsoftmobile.library.productdetail.model.ProductFeatureType
import com.tsoftmobile.library.productdetail.model.data.Product
import com.tsoftmobile.library.productdetail.model.data.Feature
import com.tsoftmobile.library.productdetail.view.fragment.*


/**
 * User: utku
 * Date: 20.11.2019
 * Time: 15:03
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

class ProductDetailFeaturesAdapter(
    fragmentManager: FragmentManager,
    val list: List<Feature>,
    val product: Product?
) : FragmentStatePagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment {
        return when (list[position].type) {
            ProductFeatureType.COMMENT_LIST -> ProductCommentsFragment.newInstance()
            ProductFeatureType.DESCRIPTION -> ProductDescriptionFragment.newInstance(
                product?.detail ?: ""
            )
            ProductFeatureType.INSTALLMENT_LIST -> ProductInstallmentListFragment.newInstance()
            ProductFeatureType.TECHNICAL_DESCRIPTION -> ProductTechnicalDescriptionFragment.newInstance()
            ProductFeatureType.PAYMENT_METHODS -> ProductPaymentMethodsFragment.newInstance()
            else -> Fragment()
        }
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].title
    }

}