package com.example.admindashboardactivity

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        val btnManageEvent = findViewById<Button>(R.id.btnManageEvent)
        val btnViewReport = findViewById<Button>(R.id.btnViewReport)

        btnManageEvent.setOnClickListener {
            Toast.makeText(this, "Manage Event Clicked", Toast.LENGTH_SHORT).show()
        }

        btnViewReport.setOnClickListener {
            Toast.makeText(this, "View Report Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}

