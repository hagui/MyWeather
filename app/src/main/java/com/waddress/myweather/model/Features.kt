package com.waddress.myweather.model

import com.squareup.moshi.Json

data class Features(@Json(name = "conditions")
                    val conditions: Int = 0)