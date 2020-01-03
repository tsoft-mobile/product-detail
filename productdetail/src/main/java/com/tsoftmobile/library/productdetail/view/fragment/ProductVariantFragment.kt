package com.tsoftmobile.library.productdetail.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tsoftmobile.library.productdetail.R
import com.tsoftmobile.library.productdetail.adapter.ProductVariantAdapter
import com.tsoftmobile.library.productdetail.model.VariantSelectListener
import com.tsoftmobile.library.productdetail.model.data.Product
import com.tsoftmobile.library.productdetail.model.data.ProductVariant
import com.tsoftmobile.library.productdetail.util.cloneArray
import com.tsoftmobile.library.productdetail.util.setOnSingleClickListener

/**
 * A simple [Fragment] subclass.
 */
class ProductVariantFragment : BottomSheetDialogFragment() {

    private var variantSelectListener: VariantSelectListener? = null

    private var selectedVariant: ProductVariant? = null

    private var items: ArrayList<ProductVariant>? = null

    private var adapter: ProductVariantAdapter? = null
    private var fragment: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragment = inflater.inflate(R.layout.fragment_product_variant, container, false)
        items = arguments?.getSerializable("variants") as ArrayList<ProductVariant>
        return fragment
    }

    //region onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setListeners()
    }
    //endregion


    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), theme)

    //mark setviews
    private fun setViews() {
        val product = arguments?.getSerializable("product") as Product
        val variantTitle = arguments?.getString("variantTitle", "")
        adapter = ProductVariantAdapter(items ?: arrayListOf(), product)
        fragment?.findViewById<RecyclerView?>(R.id.recyclerview_variant)?.setHasFixedSize(true)
        fragment?.findViewById<RecyclerView?>(R.id.recyclerview_variant)?.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        fragment?.findViewById<RecyclerView?>(R.id.recyclerview_variant)?.adapter = adapter


        fragment?.findViewById<TextView?>(R.id.text_variant_title)?.text = variantTitle
        fragment?.findViewById<TextView?>(R.id.text_product_name)?.text = product.title
        fragment?.findViewById<TextView?>(R.id.text_product_brand)?.text = product.brand
        fragment?.findViewById<TextView?>(R.id.text_product_price_sell)?.text =
            product.priceSell.toString()

        fragment?.findViewById<ImageView?>(R.id.image_product)?.let {
            Glide.with(this)
                .load(product.image.small)
                .centerInside()
                .into(it)
        }


    }

    private fun setListeners() {
        fragment?.findViewById<Button?>(R.id.button_select)?.setOnSingleClickListener {
            selectedVariant?.let { variant ->
                variantSelectListener?.invoke(variant)
            }
            dismiss()
        }
        fragment?.findViewById<ImageView?>(R.id.image_close)?.setOnSingleClickListener {
            dismiss()
        }
        adapter?.setOnItemClickListener { adapter, view, position ->
            items?.forEach {
                it.selected = false
            }
            items?.getOrNull(position)?.let {
                it.selected = true
                selectedVariant = it
                if (!it.price.isNullOrEmpty() && (it.price?.toDouble() ?: 0.0) > 0.0) {
                    fragment?.findViewById<TextView?>(R.id.text_product_price_sell)?.text = it.price
                }
            }
            adapter?.replaceData(items ?: arrayListOf<ProductVariant>())
        }
    }

    fun setOnSelectListener(listener: VariantSelectListener) {
        this.variantSelectListener = listener
    }


    companion object {
        fun newInstance(
            variants: ArrayList<ProductVariant>,
            product: Product,
            variantTitle: String
        ): ProductVariantFragment {
            val fragment = ProductVariantFragment()
            val bundle = Bundle().apply {
                putSerializable("variants", variants)
                putSerializable("product", product)
                putString("variantTitle", variantTitle)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

}
