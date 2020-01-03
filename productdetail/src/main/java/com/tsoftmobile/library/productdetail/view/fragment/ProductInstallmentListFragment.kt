package com.tsoftmobile.library.productdetail.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tsoftmobile.library.productdetail.R

/**
 * A simple [Fragment] subclass.
 */
class ProductInstallmentListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_installment_list, container, false)
    }

    companion object {
        fun newInstance(): Fragment {
            return ProductInstallmentListFragment()
        }
    }

}
