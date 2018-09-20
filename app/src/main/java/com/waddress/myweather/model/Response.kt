package com.waddress.myweather.model

import com.squareup.moshi.Json

/**
 * Created by z.HAGUI.
 */

data class Response(@Json(name = "features")
                    val features: Features,
                    @Json(name = "version")
                    val version: String = "",
                    @Json(name = "termsofService")
                    val termsofService: String = "")