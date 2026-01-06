package com.example.a1myvolunteer

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showProfileLayout()
    }

    private fun showProfileLayout() {
        setContentView(R.layout.activity_profile)
        setupNavigation()

        // 1. Setup the Change Profile button
        findViewById<Button>(R.id.btnChangeProfile)?.setOnClickListener {
            Toast.makeText(this, "Opening Gallery...", Toast.LENGTH_SHORT).show()
        }

        // 2. LOGOUT ICON LOGIC (Exit with Confirmation)
        val logoutIcon = findViewById<ImageView>(R.id.logoutIcon)
        logoutIcon?.setOnClickListener {
            // Create the popup dialog
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Exit App")
            builder.setMessage("Are you sure you want to logout and exit?")

            // If user clicks Yes
            builder.setPositiveButton("Yes") { _, _ ->
                finishAffinity() // Closes all activities and exits
            }

            // If user clicks No
            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss() // Just closes the popup
            }

            // Show the popup to the user
            val dialog = builder.create()
            dialog.show()

        }

        // 3. Dummy Activities Tab
        val activitiesTab = findViewById<TextView>(R.id.activitiesTab)
        activitiesTab?.setOnClickListener {
            // Dummy message for now
            Toast.makeText(this, "Activities feature coming soon!", Toast.LENGTH_SHORT).show()
        }
    }


    private fun showNotificationLayout() {
        setContentView(R.layout.activity_notification)
        setupNavigation()

        // If you add an exit button to the notification screen later,
        // you would copy the same btnExit code here too.
    }

    private fun setupNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigation_notif -> {
                    showNotificationLayout()
                    true
                }
                R.id.navigation_profile -> {
                    showProfileLayout()
                    true
                }
                else -> false
            }
        }
    }
}
