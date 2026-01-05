package com.example.a1myvolunteer // Corrected package name

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile) // Corrected to use the profile layout

        // 1. Find the "Change Profile" button by its ID
        val btnChangeProfile = findViewById<Button>(R.id.btnChangeProfile)

        // 2. Add a click listener to the button
        btnChangeProfile.setOnClickListener {
            // This is where you would normally open the gallery
            Toast.makeText(this, "Opening Gallery...", Toast.LENGTH_SHORT).show()
        }

        // 3. Setup Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav) // Corrected ID
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> { // Corrected ID
                    Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                // R.id.nav_notif does not exist in the menu, so it's removed.
                R.id.navigation_profile -> { // Corrected ID
                    Toast.makeText(this, "You are already on Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}