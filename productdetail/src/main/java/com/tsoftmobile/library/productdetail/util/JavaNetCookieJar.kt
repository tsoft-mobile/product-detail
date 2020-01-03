package com.tsoftmobile.library.productdetail.util

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.internal.Util
import okhttp3.internal.platform.Platform
import java.io.IOException
import java.net.CookieHandler
import java.util.*


/**
 * User: utku
 * Date: 30.10.2019
 * Time: 09:50
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */


class JavaNetCookieJar(private val cookieHandler: CookieHandler?) : CookieJar {

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        if (cookieHandler != null) {
            val cookieStrings = ArrayList<String>()
            for (cookie in cookies) {
                cookieStrings.add(cookie.toString())
            }
            val multimap = Collections.singletonMap<String, List<String>>("Set-Cookie", cookieStrings)
            try {
                cookieHandler.put(url.uri(), multimap)
            } catch (e: IOException) {
                Platform.get().log(Platform.WARN, "Saving cookies failed for " + url.resolve("/...")!!, e)
            }

        }
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        // The RI passes all headers. We don't have 'em, so we don't pass 'em!
        val headers = emptyMap<String, List<String>>()
        val cookieHeaders: Map<String, List<String>>
        try {
            cookieHeaders = cookieHandler!!.get(url.uri(), headers)
        } catch (e: IOException) {
            Platform.get().log(Platform.WARN, "Loading cookies failed for " + url.resolve("/...")!!, e)
            return emptyList()
        }

        var cookies: MutableList<Cookie>? = null
        for ((key, value) in cookieHeaders) {
            if (("Cookie".equals(key, ignoreCase = true) || "Cookie2".equals(key, ignoreCase = true)) && !value.isEmpty()) {
                for (header in value) {
                    if (cookies == null) cookies = ArrayList()
                    cookies.addAll(decodeHeaderAsJavaNetCookies(url, header))
                }
            }
        }

        return if (cookies != null)
            Collections.unmodifiableList(cookies)
        else
            emptyList()
    }


    private fun decodeHeaderAsJavaNetCookies(url: HttpUrl, header: String): List<Cookie> {
        val result = ArrayList<Cookie>()
        var pos = 0
        val limit = header.length
        var pairEnd: Int
        while (pos < limit) {
            pairEnd = Util.delimiterOffset(header, pos, limit, ";,")
            val equalsSign = Util.delimiterOffset(header, pos, pairEnd, '=')
            val name = Util.trimSubstring(header, pos, equalsSign)
            if (name.startsWith("$")) {
                pos = pairEnd + 1
                continue
            }

            // We have either name=value or just a name.
            var value = if (equalsSign < pairEnd)
                Util.trimSubstring(header, equalsSign + 1, pairEnd)
            else
                ""

            // If the value is "quoted", drop the quotes.
            if (value.startsWith("\"") && value.endsWith("\"")) {
                value = value.substring(1, value.length - 1)
            }

            result.add(
                Cookie.Builder()
                    .name(name)
                    .value(value)
                    .domain(url.host())
                    .build())
            pos = pairEnd + 1
        }
        return result
    }
}