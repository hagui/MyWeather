package com.waddress.myweather.model
import android.arch.lifecycle.LiveData
import com.waddress.myweather.datasources.database.NetworkBoundResource
import com.waddress.myweather.datasources.webservice.Resource
import com.waddress.myweather.datasources.webservice.WebService
import com.waddress.myweather.utils.AppExecutors
import com.waddress.myweather.utils.Utils
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Z.hagui
 */
@Singleton
open class WaddressRepository @Inject constructor(val webService: WebService,
                                                  val utils: Utils, val appExecutors: AppExecutors) {

    val TAG = "RestaurantRepository"

    /*open fun getWaddress(coordinates : List<Double>): LiveData<Resource<Waddress>> {
        return object : NetworkBoundResource<Waddress>(appExecutors) {

            override fun saveNetworkCallResult() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun shouldLoadFromNetwork(): Boolean {
                val shouldLoadFromNetwork = utils.hasConnection() && !coordinates.isEmpty()
                return shouldLoadFromNetwork
            }

            override fun createNetworkCall(): Call<Waddress> {
                return webService.getMyWaddress(coordinates)
            }

        }.asLiveData()
    }*/

}

/***
 *  open fun getRestaurants(cuisine: Int = 0): LiveData<Resource<List<Restaurant>>> {
return object : NetworkBoundResource<List<Restaurant>>(appExecutors) {

override fun saveNetworkCallResult(data: List<Restaurant>?) {
Log.d(TAG, "saveNetworkCallResult")
/*  data?.filterNot {
(it.cuisine !in listOf(1, 2, 3)) or
it.name.isNullOrBlank() or
(it.lat == 0.0) or
(it.lng == 0.0)
}?.forEach { restaurantsDao.insertRestaurant(it) }*/
}

override fun shouldLoadFromNetwork(data: List<Restaurant>?): Boolean {
val shouldLoadFromNetwork = utils.hasConnection() && (data == null || data.isEmpty())
Log.d(TAG, "shouldLoadFromNetwork: $shouldLoadFromNetwork")
return shouldLoadFromNetwork
}


override fun createNetworkCall(coordinates : List<Double>): Call<Waddress> {
Log.d(TAG, "createNetworkCall")
return webService.getMyWaddress(coordinates)
}
}.asLiveData()
}
 *
 */
