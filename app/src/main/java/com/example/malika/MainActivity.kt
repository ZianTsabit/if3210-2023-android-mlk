package com.example.malika

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                }
                R.id.map -> {
                    replaceFragment(R.id.frame_layout, mapFragment)
                    headerFragment.changeTitle("Cabang Restoran")
                }
                R.id.food -> {
                    replaceFragment(R.id.frame_layout, foodFragment)
                    headerFragment.changeTitle("Menu")
                }
                R.id.bucket -> {
                    replaceFragment(R.id.frame_layout, bucketFragment)
                    headerFragment.changeTitle("Keranjang")
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