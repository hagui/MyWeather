package com.waddress.myweather.model

import com.squareup.moshi.Json

/**
 * Created by z.HAGUI.
 */

data class Image(@Json(name = "link")
                 val link: String = "",
                 @Json(name = "title")
                 val title: String = "",
                 @Json(name = "url")
                 val url: String = "")