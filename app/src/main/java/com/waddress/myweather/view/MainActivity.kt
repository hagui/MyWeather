package com.waddress.myweather.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.SupportMapFragment
import com.waddress.myweather.R
import com.waddress.myweather.dagger.modules.AndroidModule
import com.waddress.myweather.utils.Utils
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_nav.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.location_bar.*
import javax.inject.Inject

class MainActivity : AbstractActivity(), NavigationView.OnNavigationItemSelectedListener {

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
                    androidManager.getLastLocation()
                }
            } else {
                Toast.makeText(this,
                        "Unable to show location - géolocalisation exigé",
                        Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

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


}
