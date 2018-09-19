package com.waddress.myweather.datasources.webservice

import com.waddress.myweather.model.Conditions
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Z.HAGUi
 * retrofit Interface
 */
interface WebService {

    //b896f62d3c17257f
    //TODO we will use the api key as static and then when we have time we will use it as parmeter
    //TODO use dynamic url in order to use the autocomplet API (when we have time)

    @GET("{apikey}/conditions/q/{codeCountry}/{city}")
    fun getWeatherByName(@Path("apiKey") apiKey : String ,
                      @Path("codeCountry") code: String,
                             @Path("city") city:String): Call<Conditions>


}