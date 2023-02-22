package com.example.malika

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.service.autofill.OnClickAction
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val headerFragment = HeaderFragment()
    private val cameraFragment = CameraFragment()
    private val mapFragment = MapFragment()
    private val foodFragment = FoodFragment()
    private val bucketFragment = BucketFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                    headerFragment.setTemperature("28°C")
                    headerFragment.unhideTemperature()
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
}