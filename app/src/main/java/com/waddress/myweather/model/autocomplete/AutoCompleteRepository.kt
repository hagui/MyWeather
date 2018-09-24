package com.waddress.myweather.model.autocomplete

import android.arch.lifecycle.LiveData
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import com.waddress.myweather.datasources.database.NetworkBoundResource
import com.waddress.myweather.datasources.webservice.Resource
import com.waddress.myweather.datasources.webservice.WebService
import com.waddress.myweather.model.Weather
import com.waddress.myweather.utils.AppExecutors
import com.waddress.myweather.utils.Utils
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.HttpUrl
import retrofit2.http.Url


/**
 * Created by Z.hagui
 */
@Singleton
open class AutoCompleteRepository @Inject constructor(val webService: WebService,
                                                      val utils: Utils, val appExecutors: AppExecutors) {


    open fun getCountryList(url: String): LiveData<Resource<Response>> {
        return object : NetworkBoundResource<Response>(appExecutors) {

            /**
             * insert search result in DB
             * require Android version >=o because of date format
             */
            @RequiresApi(Build.VERSION_CODES.O)
            override fun saveNetworkCallResult(data: Response?) {
                if (data != null) {
                    //TODO save in DB if needed
                }
            }

            override fun shouldLoadFromNetwork(data: Response?): Boolean {
                Log.d("check connection", "vérification de connexion")
                return utils.hasConnection()
            }

            /**
             * search saved value if we arenot connected
             */
            override fun loadFromDatabase(): LiveData<List<Weather>>? {
                Log.d("serach in db", "db model")
                return null
            }

            /**
             * search temperature using City parameter
             */
            override fun createNetworkCall(): Call<Response> {

                print("avant buildr $url")
                val urlBuilder = HttpUrl.parse(url)!!.newBuilder()
                val urls = urlBuilder.build().toString()
                print("apres buildr $urls")

                /*val url = HttpUrl.Builder()
                        .scheme("https")
                        .host("www.google.com")
                        .addPathSegment("search")
                        .addQueryParameter("q", "polar bears")
                        .build()*/
                return webService.autoComplete(urls)
            }

        }.asLiveData()
    }

}

