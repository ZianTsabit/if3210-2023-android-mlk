package com.example.malika

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.service.autofill.OnClickAction
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), SensorEventListener {
    private val headerFragment = HeaderFragment()
    private val cameraFragment = CameraFragment()
    private val mapFragment = MapFragment()
    private val foodFragment = FoodFragment()
    private val bucketFragment = BucketFragment()

    private lateinit var sensorManager: SensorManager
    private var temperature: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

        if (temperature == null) {
            Log.e("SENSOR", "Sensor.TYPE_AMBIENT_TEMPERATURE is not available on your device")
        } else {
            sensorManager.registerListener(this, temperature, SensorManager.SENSOR_DELAY_NORMAL)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        replaceFragment(R.id.frame_header, headerFragment)
        replaceFragment(R.id.frame_layout, cameraFragment)

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.camera -> {
                    replaceFragment(R.id.frame_layout, cameraFragment)
                    headerFragment.changeTitle("Twibbon")
                    headerFragment.hideTemperature()
                }
                R.id.map -> {
                    replaceFragment(R.id.frame_layout, mapFragment)
                    headerFragment.changeTitle("Cabang Restoran")
                    headerFragment.hideTemperature()
                }
                R.id.food -> {
                    replaceFragment(R.id.frame_layout, foodFragment)
                    headerFragment.changeTitle("Menu")

                    if (temperature != null) {
                        headerFragment.unhideTemperature()
                    }
                }
                R.id.bucket -> {
                    replaceFragment(R.id.frame_layout, bucketFragment)
                    headerFragment.changeTitle("Keranjang")
                    headerFragment.hideTemperature()
                }
            }
            true
        }
    }

    private fun replaceFragment(frame: Int, fragment: Fragment) {
        if (fragment != null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(frame, fragment)
            fragmentTransaction.commit()
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        val celciusOfTemperature = event.values[0]
        headerFragment.setTemperature("$celciusOfTemperature°C")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//        TODO("Not yet implemented")
    }
}