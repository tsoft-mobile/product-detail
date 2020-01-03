package com.tsoftmobile.library.productdetail.view.fragment

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

import com.tsoftmobile.library.productdetail.R
import cz.kinst.jakub.view.SimpleStatefulLayout

/**
 * A simple [Fragment] subclass.
 */
class ProductDescriptionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.fragment_product_description, container, false)
        val stateView = fragment.findViewById<SimpleStatefulLayout>(R.id.state_view)
        val webView = fragment.findViewById<WebView>(R.id.web_view)
        val url = arguments?.getString("url", "")!!
        stateView.isTransitionsEnabled = false
        stateView.showProgress()
        webView.settings.builtInZoomControls = true
        webView.settings.defaultTextEncodingName = "utf-8"
        webView.settings.javaScriptEnabled = true
        webView.settings.displayZoomControls = false
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                stateView.showProgress()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                stateView.showContent()
            }
        }
        webView.loadUrl(url)

        return fragment
    }

    companion object {
        fun newInstance(url: String): Fragment {
            val fragment = ProductDescriptionFragment()
            fragment.arguments = Bundle().apply {
                putString("url", url)
            }
            return fragment
        }
    }

}
