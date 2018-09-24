package com.waddress.myweather.model.autocomplete
import com.squareup.moshi.Json

data class ResultItem(

	@Json(name="c")
	val C: String? = null,

	@Json(name="zmw")
	val zmw: String? = null,

	@Json(name="tz")
	val tz: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="lon")
	val lon: String? = null,

	@Json(name="type")
	val type: String? = null,

	@Json(name="tzs")
	val tzs: String? = null,

	@Json(name="l")
	val L: String? = null,

	@Json(name="lat")
	val lat: String? = null
)