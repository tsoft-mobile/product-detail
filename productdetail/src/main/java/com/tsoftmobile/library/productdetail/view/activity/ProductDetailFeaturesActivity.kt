package com.tsoftmobile.library.productdetail.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.tsoftmobile.library.productdetail.R
import com.tsoftmobile.library.productdetail.adapter.ProductDetailFeaturesAdapter
import com.tsoftmobile.library.productdetail.model.ProductFeatureType
import com.tsoftmobile.library.productdetail.model.data.Product
import com.tsoftmobile.library.productdetail.model.data.Feature

class ProductDetailFeaturesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail_features)

        val featuresList: List<Feature> =
            intent.getSerializableExtra("featureList") as List<Feature>
        val selectedFeature = intent.getSerializableExtra("selected") as ProductFeatureType
        val product=intent.getSerializableExtra("product") as Product?

        val adapter = ProductDetailFeaturesAdapter(
            supportFragmentManager,
            featuresList,
            product
        )
        val viewPager = findViewById<ViewPager?>(R.id.view_pager)
        viewPager?.adapter = adapter
        findViewById<TabLayout?>(R.id.tab_layout)?.setupWithViewPager(viewPager)
        viewPager?.currentItem = featuresList.indexOfFirst {
            it.type == selectedFeature
        }

    }

    companion object {
        fun newInstance(
            context: Context,
            featuresList: ArrayList<Feature>,
            selected: ProductFeatureType,
            product:Product?
        ) {
            val intent = Intent(context, ProductDetailFeaturesActivity::class.java)
            intent.putExtra("featureList", featuresList)
            intent.putExtra("selected", selected)
            intent.putExtra("product", product)
            context.startActivity(intent)
        }
    }
}
