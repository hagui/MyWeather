package com.waddress.myweather.model.autocomplete
import com.squareup.moshi.Json

data class Response(

	@Json(name="RESULTS")
	val result: List<ResultItem?>? = null
)