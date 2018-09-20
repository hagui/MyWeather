package com.waddress.myweather.model

import com.squareup.moshi.Json
import java.io.Serializable


/**
 * Created by z.HAGUI.
 */
data class DisplayLocation(@Json(name = "zip")
                           val zip: String = "",
                           @Json(name = "magic")
                           val magic: String = "",
                           @Json(name = "elevation")
                           val elevation: String = "",
                           @Json(name = "country")
                           val country: String = "",
                           @Json(name = "country_iso3166")
                           val countryIso: String = "",
                           @Json(name = "city")
                           val city: String = "",
                           @Json(name = "state_name")
                           val stateName: String = "",
                           @Json(name = "latitude")
                           val latitude: String = "",
                           @Json(name = "wmo")
                           val wmo: String = "",
                           @Json(name = "state")
                           val state: String = "",
                           @Json(name = "full")
                           val full: String = "",
                           @Json(name = "longitude")
                           val longitude: String = ""): Serializable