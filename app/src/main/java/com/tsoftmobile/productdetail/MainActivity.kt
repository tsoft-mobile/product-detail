package com.tsoftmobile.productdetail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tsoftmobile.library.productdetail.model.LayoutButtonType
import com.tsoftmobile.library.productdetail.model.ProductFeatureType
import com.tsoftmobile.library.productdetail.model.data.Feature
import com.tsoftmobile.library.productdetail.model.data.LayoutButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        product_detail.url = ""
        product_detail.token = ""
        product_detail.productId = ""

        val buttonItems: ArrayList<LayoutButton> = arrayListOf()
        buttonItems.add(
            LayoutButton(
                LayoutButtonType.ADD_TO_FAVORITE,
                "Favorilere Ekle",
                drawableImage = R.drawable.ic_favorite_border_black_24dp
            )
        )
        buttonItems.add(
            LayoutButton(
                LayoutButtonType.SHARE,
                "Paylaş",
                drawableImage = R.drawable.ic_share_black_24dp
            )
        )
        buttonItems.add(
            LayoutButton(
                LayoutButtonType.PRICE_ALARM,
                "Fiyat Alarm",
                drawableImage = R.drawable.ic_alarm_black_24dp
            )
        )
        product_detail.buttonItems = buttonItems

        val featureItems: ArrayList<Feature> = arrayListOf()

        featureItems.add(Feature("Ürün Açıklamaları", ProductFeatureType.DESCRIPTION))
        featureItems.add(Feature("Teknik Özellikler", ProductFeatureType.TECHNICAL_DESCRIPTION))
        featureItems.add(Feature("Yorumlar", ProductFeatureType.COMMENT_LIST))

        product_detail.featureItems = featureItems

        product_detail.setOnLayoutButtonItemClickListener {
            Toast.makeText(this, product_detail.buttonItems[it].text, Toast.LENGTH_LONG).show()
        }

        product_detail.setOnButtonAddToCartClickListener {
            Toast.makeText(this, "addToCart", Toast.LENGTH_LONG).show()
        }
        product_detail.setOnButtonMinusClickListener {
            Toast.makeText(this, "minus", Toast.LENGTH_LONG).show()
        }
        product_detail.setOnButtonPlusClickListener {
            Toast.makeText(this, "plus", Toast.LENGTH_LONG).show()
        }

        product_detail.setOnFeatureItemClickListener {
            Toast.makeText(
                this,
                product_detail.featureItems[it].title,
                Toast.LENGTH_LONG
            )
                .show()
        }

        product_detail.fetchProductDetail()
    }
}
