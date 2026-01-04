package com.example.ict602my_vol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ict602my_vol.ui.theme.ICT602MY_VOLTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ICT602MY_VOLTheme {
                VolunteerScreen()
            }
        }
    }
}

@Composable
fun VolunteerScreen() {
    var selectedRole by remember { mutableStateOf("Volunteer") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3ABABE))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Admin / Volunteer toggle
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { selectedRole = "Admin" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedRole == "Admin") Color.White else Color(0xFF3ABABE),
                    contentColor = if (selectedRole == "Admin") Color(0xFF3ABABE) else Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text("Admin")
            }

            Button(
                onClick = { selectedRole = "Volunteer" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedRole == "Volunteer") Color.White else Color(0xFF3ABABE),
                    contentColor = if (selectedRole == "Volunteer") Color(0xFF3ABABE) else Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text("Volunteer")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Logo placeholder (white text on blue)
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("LOGO", color = Color(0xFF3ABABE), fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Title / subtitle
        Text(
            if (selectedRole == "Admin") "Admin Sign Up" else "Join as Volunteer",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            if (selectedRole == "Admin")
                "Fill in your admin details"
            else
                "Fill in your details to start volunteering",
            color = Color.White,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Input fields
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Nationality") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Handle Sign Up */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Sign Up")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "By clicking Sign Up, you agree to our Terms of Service and Privacy Policy",
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VolunteerScreenPreview() {
    ICT602MY_VOLTheme {
        VolunteerScreen()
    }
}


