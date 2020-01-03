package com.tsoftmobile.library.productdetail.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tsoftmobile.library.productdetail.R

class ProductCommentsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_comments, container, false)
    }

    companion object {
        fun newInstance(): Fragment {
            val fragment = ProductCommentsFragment()
            fragment.arguments = Bundle().apply {

            }
            return fragment
        }
    }

}
