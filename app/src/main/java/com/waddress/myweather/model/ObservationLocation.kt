package com.waddress.myweather.model

import com.squareup.moshi.Json

data class ObservationLocation(@Json(name = "elevation")
                               val elevation: String = "",
                               @Json(name = "country")
                               val country: String = "",
                               @Json(name = "country_iso3166")
                               val countryIso: String = "",
                               @Json(name = "city")
                               val city: String = "",
                               @Json(name = "latitude")
                               val latitude: String = "",
                               @Json(name = "state")
                               val state: String = "",
                               @Json(name = "full")
                               val full: String = "",
                               @Json(name = "longitude")
                               val longitude: String = "")