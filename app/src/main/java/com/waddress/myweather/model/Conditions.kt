package com.waddress.myweather.model

import com.squareup.moshi.Json

/**
 * Created by z.HAGUI.
 */

data class Conditions(@Json(name = "response")
                      val response: Response,
                      @Json(name = "current_observation")
                      val currentObservation: CurrentObservation)