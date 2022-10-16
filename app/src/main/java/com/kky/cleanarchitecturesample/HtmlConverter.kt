package com.kky.cleanarchitecturesample

import android.os.Build
import android.text.Html

object HtmlConverter {
    fun fromHtml(html: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT).toString()
        } else {
            Html.fromHtml(html).toString()
        }
    }
}