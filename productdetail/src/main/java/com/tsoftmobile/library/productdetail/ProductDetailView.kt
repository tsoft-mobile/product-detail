package com.tsoftmobile.library.productdetail

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Point
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import com.tsoftmobile.library.productdetail.adapter.ButtonAdapter
import com.tsoftmobile.library.productdetail.adapter.FeatureAdapter
import com.tsoftmobile.library.productdetail.adapter.ImagePagerAdapter
import com.tsoftmobile.library.productdetail.adapter.ProductAdapter
import com.tsoftmobile.library.productdetail.model.ImageSizeType
import com.tsoftmobile.library.productdetail.model.ImageTypeByPlace
import com.tsoftmobile.library.productdetail.model.Status
import com.tsoftmobile.library.productdetail.model.data.Feature
import com.tsoftmobile.library.productdetail.model.data.LayoutButton
import com.tsoftmobile.library.productdetail.model.data.Product
import com.tsoftmobile.library.productdetail.model.data.ProductVariant
import com.tsoftmobile.library.productdetail.repository.ProductDetailRepository
import com.tsoftmobile.library.productdetail.util.cloneArray
import com.tsoftmobile.library.productdetail.util.gone
import com.tsoftmobile.library.productdetail.util.setOnSingleClickListener
import com.tsoftmobile.library.productdetail.view.fragment.ProductVariantFragment
import cz.kinst.jakub.view.SimpleStatefulLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList


/**
 * User: utku
 * Date: 30.10.2019
 * Time: 20:58
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

class ProductDetailView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val TAG = javaClass.simpleName

    private var mContext: Context = getContext()
    private var mLayout: Int = R.layout.layout_product_detail_view
    private var mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)
    private var typedArray: TypedArray? = null

    private val productDetailRepository = ProductDetailRepository()

    var product: Product? = null

    var productId: String? = null

    var token: String? = null
        set(value) {
            field = value
            Config.TOKEN = value ?: ""
        }

    var url: String? = null
        set(value) {
            field = value
            Config.SERVICE_BASE_URL = value ?: ""
        }

    var imageSizeDetail = ImageSizeType.big
        set(value) {
            field = value
            Config.imageSizeDetail = value
        }
    var imageSizeList = ImageSizeType.medium
        set(value) {
            field = value
            Config.imageSizeList = value
        }
    var appMainColorRes = ContextCompat.getColor(context, R.color.colorPrimary)


    private var productBrandView: TextView? = null
    private var productNameView: TextView? = null
    private var productCodeView: TextView? = null
    private var productPriceSellView: TextView? = null
    private var stateView: SimpleStatefulLayout? = null
    private var stateViewSameProducts: SimpleStatefulLayout? = null

    private var emptyView: View? = null
    var emptyViewId: Int? = null
        set(value) {
            field = value
            if (value != null && value != -1) {
                emptyView = LayoutInflater.from(mContext).inflate(value, null)
            }
        }

    private var loadingView: View? = null
    var loadingViewId: Int? = null
        set(value) {
            field = value
            if (value != null && value != -1) {
                loadingView = LayoutInflater.from(mContext).inflate(value, null)
            }
        }

    var appType: AppType = AppType.Other

    private val screenWidth: Int by lazy {
        val wm = getContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        size.x
    }

    private val imageAdapter = ImagePagerAdapter(arrayListOf(), ImageTypeByPlace.Detail)


    @ColorRes
    var imageSliderDotsColorRes: Int = -1
        set(value) {
            field = value
            findViewById<WormDotsIndicator>(R.id.view_dots_indicator).setDotIndicatorColor(value)
        }

    var selectedVariant1: ProductVariant? = null
    var selectedVariant2: ProductVariant? = null

    @LayoutRes
    var sameProductItemLayout: Int = R.layout.item_product

    private var sameProductAdapter: ProductAdapter? = null

    var buttonItems: ArrayList<LayoutButton> = arrayListOf()
        set(value) {
            field = value
            replaceLayoutButtonsData()
        }

    var featureItems: ArrayList<Feature> = arrayListOf()
        set(value) {
            field = value
            replaceFeaturesData()
        }

    val buttonAdapter = ButtonAdapter(buttonItems)

    val featureAdapter = FeatureAdapter(featureItems)

    //--------------- INIT ------------------

    init {
        typedArray =
            mContext.theme.obtainStyledAttributes(
                attrs,
                R.styleable.ProductDetailView,
                defStyleAttr,
                defStyleAttr
            )

        setup()

        if (!url.isNullOrEmpty() && !token.isNullOrEmpty() && productId.isNullOrEmpty()) {
            fetchProductDetail()
        }
    }


    private fun setup() {
        mLayout = typedArray?.getResourceId(
            R.styleable.ProductDetailView_custom_view,
            R.layout.layout_product_detail_view
        ) ?: R.layout.layout_product_detail_view

        inflateLayout()
        productNameView = findViewById(R.id.text_product_name)
        productBrandView = findViewById(R.id.text_product_brand)
        productCodeView = findViewById(R.id.text_product_code)
        productPriceSellView = findViewById(R.id.text_product_price_sell)
        stateView = findViewById(R.id.state_view)
        stateView?.isTransitionsEnabled = false
        stateViewSameProducts = findViewById(R.id.state_view_same_products)
        stateViewSameProducts?.isTransitionsEnabled = false

        if (productId.isNullOrEmpty()) {
            productId = typedArray?.getString(R.styleable.ProductDetailView_product_id)
        }

        if (url.isNullOrEmpty()) {
            url = typedArray?.getString(R.styleable.ProductDetailView_url)
        }

        if (token.isNullOrEmpty()) {
            token = typedArray?.getString(R.styleable.ProductDetailView_token)
        }

        if (emptyView == null) {
            emptyViewId = typedArray?.getResourceId(R.styleable.ProductDetailView_empty_view, -1)

        }

        if (loadingView == null) {
            loadingViewId =
                typedArray?.getResourceId(R.styleable.ProductDetailView_loading_view, -1)
        }

        if (!url.isNullOrEmpty() && !token.isNullOrEmpty() && productId.isNullOrEmpty()) {
            fetchProductDetail()
        }
        setAddToCart()
        setListeners()

//        findViewById<RecyclerView>(R.id.recyclerview_buttons).adapter = buttonsAdapter
        buttonAdapter.bindToRecyclerView(findViewById<RecyclerView>(R.id.recyclerview_button))
        featureAdapter.bindToRecyclerView(findViewById<RecyclerView>(R.id.recyclerview_feature))
    }

    private fun inflateLayout() {
        mLayoutInflater = LayoutInflater.from(mContext)
        mLayoutInflater.inflate(mLayout, this, true)
    }

    @SuppressLint("CheckResult")
    fun fetchProductDetail() {
        if (!productId.isNullOrEmpty() && !token.isNullOrEmpty() && !url.isNullOrEmpty()) {
            productDetailRepository.getProductDetail(productId!!).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.status == Status.SUCCESS) {
                        if (it.data?.success == true) {
                            it.data.data?.getOrNull(0)?.let { product ->
                                this@ProductDetailView.product = product
                                setProductBrand(product.brand)
                                setProductName(product.title)
                                setProductCode(product.productCode)
                                setProductPriceSell(product.priceSell)
                                setProductVariant1Title(product.variantFeature1Title ?: "")
                                setProductVariant2Title(product.variantFeature2Title ?: "")
                                if (product.hasVariant != "1") {
                                    setProductVariant1Visibility(false)
                                    setProductVariant2Visibility(false)
                                }
                                setViewPagerSize()
                                setImages(true)
                            }
                            runOnUiThread {
                                stateView?.showContent()
                            }
                        } else {
                            showToast("Failure:${it.data?.message?.get(0)?.text?.get(0)}")
                        }
                    } else if (it.status == Status.ERROR) {
                        showToast("Failure:${it.message}")
                    } else if (it.status == Status.LOADING) {
                        stateView?.showProgress()
                    }
                }

        }
    }

    private fun setViewsSameProducts(data: ArrayList<Product>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_same_products)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)
        runOnUiThread {
            sameProductAdapter = ProductAdapter(data, sameProductItemLayout)
            recyclerView.adapter = sameProductAdapter
            stateViewSameProducts?.showContent()
        }
    }

    private fun runOnUiThread(block: () -> Unit) {
        rootView.post {
            block.invoke()
        }
    }

    fun <T : View> getView(@IdRes id: Int): T? {
        if (id == -1) {
            return null
        }
        return findViewById<T>(id)
    }

    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun setProductName(str: String) {
        runOnUiThread {
            productNameView?.text = str
        }
    }

    private fun setProductBrand(str: String) {
        runOnUiThread {
            productBrandView?.text = str
        }
    }

    private fun setProductPriceSell(price: Double) {
        runOnUiThread {

            findViewById<TextView?>(R.id.text_view_discount_percent)?.gone()
            findViewById<TextView?>(R.id.price_not_discounted)?.gone()

            var priceSellT = String.format(
                Locale("tr", "TR"),
                "%,." + Config.virguleChar + "f",
                price
            ) + " " + product?.targetCurrency


            productPriceSellView?.setTextColor(appMainColorRes)
            productPriceSellView?.text = priceSellT
        }
    }

    private fun setProductCode(str: String) {
        runOnUiThread {
            productCodeView?.text = str
        }
    }

    private fun setProductVariant1Title(str: String) {
        runOnUiThread {
            findViewById<TextView?>(R.id.text_variant1_title)?.text = str
        }
    }

    private fun setProductVariant1Visibility(visible: Boolean) {
        runOnUiThread {
            if (visible) {
                findViewById<View?>(R.id.layout_variant1)?.visibility = View.VISIBLE
            } else {
                findViewById<View?>(R.id.layout_variant1)?.visibility = View.GONE
            }
        }
    }

    private fun setProductVariant2Title(str: String) {
        runOnUiThread {
            findViewById<TextView?>(R.id.text_variant2_title)?.text = str
        }
    }

    private fun setProductVariant2Visibility(visible: Boolean) {
        runOnUiThread {
            if (visible) {
                findViewById<View?>(R.id.layout_variant2)?.visibility = View.VISIBLE
            } else {
                findViewById<View?>(R.id.layout_variant2)?.visibility = View.GONE
            }
        }
    }

    private fun setImages(variantActive: Boolean) {
        val viewPager = findViewById<ViewPager>(R.id.view_pager_images)
        runOnUiThread {
            viewPager.adapter = imageAdapter
            findViewById<WormDotsIndicator?>(R.id.view_dots_indicator)?.setViewPager(viewPager)
            if (variantActive) {
                imageAdapter.replaceData(product?.imageList ?: arrayListOf())
            }
        }
    }


    private fun setAddToCart() {
        val background = GradientDrawable()
        background.setColor(appMainColorRes)
        background.cornerRadius = 8f
        background.shape = GradientDrawable.RECTANGLE
        findViewById<LinearLayout?>(R.id.add_to_cart)?.background = background
    }


    private fun setViewPagerSize() {
        val viewPager = findViewById<ViewPager?>(R.id.view_pager_images)
        runOnUiThread {
            if (appType == AppType.Clothing) {
                val ratio =
                    if ((product?.imageRatio ?: 0.0) < 1.0) (product?.imageRatio ?: 0.0) else 1.0
                viewPager?.layoutParams?.width = screenWidth
                viewPager?.layoutParams?.height = ((screenWidth) / ratio).toInt()
            } else {
                viewPager?.layoutParams = LinearLayout.LayoutParams(screenWidth, screenWidth)
            }
        }
    }


    private fun setListeners() {

        findViewById<View?>(R.id.layout_variant1_select)?.setOnClickListener {
            product?.run {
                val variants = product?.variants?.cloneArray() ?: arrayListOf()
                val fragment = ProductVariantFragment.newInstance(
                    variants,
                    this,
                    this.variantFeature1Title ?: ""
                )
                fragment.setOnSelectListener {
                    selectedVariant1 = it
                    selectedVariant2 = null
                    clearVariant2Selected(it.variantId)
                    runOnUiThread {
                        findViewById<TextView?>(R.id.text_variant1)?.text = it.type
                        findViewById<TextView?>(R.id.text_variant2)?.text = ""
                    }
                    setOnVariant1SelectListener?.invoke(it)
                }
                fragment.show(
                    (context as AppCompatActivity).supportFragmentManager,
                    fragment::class.java.simpleName
                )
            }

        }

        findViewById<View?>(R.id.layout_variant2_select)?.setOnClickListener {
            if (selectedVariant1 == null) {
                showToast("variant 1")
            } else {
                product?.run {
                    val variants = selectedVariant1?.children?.cloneArray() ?: arrayListOf()
                    val fragment = ProductVariantFragment.newInstance(
                        variants,
                        this,
                        this.variantFeature2Title ?: ""
                    )
                    fragment.setOnSelectListener {
                        selectedVariant2 = it
                        runOnUiThread {
                            findViewById<TextView?>(R.id.text_variant2)?.text = it.type
                        }
                        setOnVariant2SelectListener?.invoke(it)
                    }
                    fragment.show(
                        (context as AppCompatActivity).supportFragmentManager,
                        fragment::class.java.simpleName
                    )
                }
            }
        }

        findViewById<ImageView?>(R.id.button_minus)?.setOnSingleClickListener {
            setOnButtonMinusClickListener?.invoke()
        }

        findViewById<ImageView?>(R.id.button_plus)?.setOnSingleClickListener {
            setOnButtonPlusClickListener?.invoke()
        }

        findViewById<LinearLayout?>(R.id.add_to_cart)?.setOnSingleClickListener {
            setOnButtonAddToCartClickListener?.invoke()
        }


        featureAdapter.setOnItemClickListener { _, _, position ->
            setOnFeatureItemClickListener?.invoke(position)
        }

        buttonAdapter.setOnItemClickListener { _, _, position ->
            setOnLayoutButtonItemClickListener?.invoke(position)
        }

    }

    private fun clearVariant1Selected() {
        product?.variants?.forEach {
            it.selected = false
        }
    }

    private fun clearVariant2Selected(variantId: String) {
        product?.variants?.forEach {
            if (it.variantId == variantId) {
                it.children?.forEach { child ->
                    child.selected = false
                }
            }
        }
    }

    private var setOnVariant1SelectListener: ((variant: ProductVariant) -> Unit)? = null
    fun setOnVariant1SelectListener(listener: (variant: ProductVariant) -> Unit) {
        this.setOnVariant1SelectListener = listener
    }

    private var setOnVariant2SelectListener: ((variant: ProductVariant) -> Unit)? = null
    fun setOnVariant2SelectListener(listener: (variant: ProductVariant) -> Unit) {
        this.setOnVariant2SelectListener = listener
    }

    private fun replaceLayoutButtonsData() {
        buttonAdapter.replaceData(buttonItems)
    }

    private fun replaceFeaturesData() {
        featureAdapter.replaceData(featureItems)
    }

    private var setOnButtonMinusClickListener: (() -> Unit)? = null
    fun setOnButtonMinusClickListener(listener: () -> Unit) {
        this.setOnButtonMinusClickListener = listener
    }

    private var setOnButtonPlusClickListener: (() -> Unit)? = null
    fun setOnButtonPlusClickListener(listener: () -> Unit) {
        this.setOnButtonPlusClickListener = listener
    }

    private var setOnButtonAddToCartClickListener: (() -> Unit)? = null
    fun setOnButtonAddToCartClickListener(listener: () -> Unit) {
        this.setOnButtonAddToCartClickListener = listener
    }

    private var setOnFeatureItemClickListener: ((position: Int) -> Unit)? = null
    fun setOnFeatureItemClickListener(listener: (position: Int) -> Unit) {
        this.setOnFeatureItemClickListener = listener
    }

    private var setOnLayoutButtonItemClickListener: ((position: Int) -> Unit)? = null
    fun setOnLayoutButtonItemClickListener(listener: (position: Int) -> Unit) {
        this.setOnLayoutButtonItemClickListener = listener
    }


}