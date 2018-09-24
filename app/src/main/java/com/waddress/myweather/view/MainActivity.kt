package com.waddress.myweather.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.waddress.myweather.R
import com.waddress.myweather.dagger.modules.AndroidModule
import com.waddress.myweather.model.City
import com.waddress.myweather.model.Conditions
import com.waddress.myweather.utils.ResourceObserver
import com.waddress.myweather.utils.Utils
import com.waddress.myweather.viewmodels.AutoCompleteViewModel
import com.waddress.myweather.viewmodels.ViewModelFactory
import com.waddress.myweather.viewmodels.WeatherViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_nav.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.location_bar.*
import javax.inject.Inject
import android.support.v7.widget.SearchView.SearchAutoComplete
import android.text.Editable
import android.widget.AdapterView
import android.widget.ArrayAdapter


/**
 * Created by z.HAGUI.
 */

class MainActivity : AbstractActivity(), NavigationView.OnNavigationItemSelectedListener {


    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: MainActivity
        var mLocation: Location? = null
    }

    init {
        instance = this
    }

    private var searchView: SearchView? = null
    private var mSearchAutoComplete: SearchView.SearchAutoComplete? = null
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var autoCompleteViewModel: AutoCompleteViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val LOCATION_REQUEST_CODE = 101
    @Inject
    lateinit var utils: Utils

    @Inject
    lateinit var androidManager: AndroidModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)
        AndroidInjection.inject(this)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        this.floatMenuAction()
        this.floatLocationAction()
        weatherViewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)
        autoCompleteViewModel = ViewModelProviders.of(this, viewModelFactory).get(AutoCompleteViewModel::class.java)
    }

    private fun floatMenuAction() {
        menuActionButton.setOnClickListener { view ->
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            } else {
                drawer_layout.openDrawer(GravityCompat.START)
            }
        }
    }


    //TODO  le button n'est pas défini si API<17
    //TODO geoloc fait  l'ancienne il faut le modifier
    private fun floatLocationAction() {

        (if (floatingActionButton != null) floatingActionButton
        else throw NullPointerException("Expression " +
                "'floatingActionButton' must not be null")).setOnClickListener { view ->
            if (androidManager.locationEnabled()) {
                val permission = ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    requestPermission(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            LOCATION_REQUEST_CODE)
                } else {
                    // androidManager.getLastLocation()
                    androidManager.provideLocationManager().requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
                    /* weatherViewModel.weatherInput.value = getCityFromGeoloc()
                     weatherViewModel.weather.observe(this, ResourceObserver("MainActivity",
                             hideLoading = ::hideLoading,
                             onSuccess = ::showWeather,
                             showLoading = ::showLoading,

                             onError = ::showErrorMessage))*/


                }
            } else {
                Toast.makeText(this,
                        "Unable to show location - géolocalisation exigé",
                        Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun getCityFromGeoloc(): City {
        try {
            // Request location updates in order to get
            androidManager.provideLocationManager().requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
            if (MainActivity.mLocation != null) {
                androidManager.getGeocoder().getFromLocation(mLocation!!.latitude, mLocation!!.longitude, 1)
            }
            Toast.makeText(this,
                    " show location - $MainActivity.mLocation",
                    Toast.LENGTH_LONG).show()
            print(" show location - $MainActivity.mLocation")
            val name = androidManager.getGeocoder().getFromLocation(MainActivity.mLocation!!.latitude, MainActivity.mLocation!!.longitude, 1).get(0).locality
            val coun = androidManager.getGeocoder().getFromLocation(MainActivity.mLocation!!.latitude, MainActivity.mLocation!!.longitude, 1).get(0).countryName
            return City(coun, "$name.json")
        } catch (ex: SecurityException) {
            return City("FRANCE", "Paris.json")

        }
    }


    private fun showWeather(conditions: Conditions) {
        progressbar.visibility = View.GONE
        locationTextView.text = conditions.currentObservation.displayLocation.city
        Toast.makeText(this, "success", Toast.LENGTH_LONG).show()
    }

    private fun showErrorMessage(error: String) {
        Toast.makeText(this, "Error: $error", Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        progressbar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progressbar.visibility = View.GONE
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_search -> {

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val item: MenuItem = menu.findItem(R.id.action_search)
        searchView = MenuItemCompat.getActionView(item) as SearchView
        mSearchAutoComplete = searchView!!.findViewById(android.support.v7.appcompat.R.id.search_src_text)
                as SearchView.SearchAutoComplete

        mSearchAutoComplete!!.dropDownAnchor = R.id.action_search
        mSearchAutoComplete!!.threshold = 0
        mSearchAutoComplete!!.setDropDownBackgroundResource(android.R.color.holo_purple)

        val items = listOf("1", "2", " 3")
        var adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, items.toList())
        mSearchAutoComplete!!.setAdapter(adapter)

        mSearchAutoComplete!!.setOnItemClickListener { parent, view, position, id ->

            val itemAtPosition = parent.getItemAtPosition(position)
            searchView!!.setQuery(itemAtPosition.toString(), true)

        }



        return super.onCreateOptionsMenu(menu)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }

            R.id.nav_slideshow -> {

            }
            R.id.nav_share -> {

            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    //TODO migration des demande des permission to dagger Module
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,
                            "Unable to show location - permission required",
                            Toast.LENGTH_LONG).show()
                } else {
                    if (!androidManager.locationEnabled()) {
                        Toast.makeText(this,
                                "Unable to show location - géolocalisation exigé",
                                Toast.LENGTH_LONG).show()
                    }


                }
            }
        }
    }

    private fun requestPermission(permissionType: String,
                                  requestCode: Int) {
        ActivityCompat.requestPermissions(this,
                arrayOf(permissionType), requestCode
        )
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            print(" show location - ${location.latitude}")
            print(" show location - ${location.longitude}")

            MainActivity.mLocation = location
            print(" show location - ${MainActivity.mLocation!!.latitude}")
            print(" show location - ${MainActivity.mLocation!!.longitude}")
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }


}
