package com.waddress.myweather.datasources.webservice

import com.waddress.myweather.model.Conditions
import com.waddress.myweather.model.autocomplete.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url
import okhttp3.HttpUrl

/**
 * Created by Z.HAGUi
 * retrofit Interface
 */
interface WebService {

    //b896f62d3c17257f
    //TODO we will use the api key as static and then when we have time we will use it as parmeter
    //TODO use dynamic url in order to use the autocomplet API (when we have time)

   /* @GET("{apikey}/conditions/q/{codeCountry}/{city}")
    fun getWeatherByName(@Path("apiKey") apiKey : String ,
                      @Path("codeCountry") code: String,
                             @Path("city") city:String): Call<Conditions>*/

    @GET("b896f62d3c17257f/conditions/q/{codeCountry}/{city}")
    fun getWeatherByName(@Path("codeCountry") code: String,
                         @Path("city") city:String): Call<Conditions>

    //GET http://autocomplete.wunderground.com/aq?query=query
    @GET
    fun autoComplete(@Url url: HttpUrl): Call<Response>
}