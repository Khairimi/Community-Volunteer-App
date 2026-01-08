package com.example.ict602my_vol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.ict602my_vol.ui.theme.ICT602MY_VOLTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ICT602MY_VOLTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var selectedRole by remember { mutableStateOf("Volunteer") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Toggle buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { selectedRole = "Volunteer" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedRole == "Volunteer") Color(0xFF3ABABE) else Color.White,
                    contentColor = if (selectedRole == "Volunteer") Color.White else Color(0xFF3ABABE)
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text("Volunteer")
            }

            Button(
                onClick = { selectedRole = "Admin" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedRole == "Admin") Color(0xFF3ABABE) else Color.White,
                    contentColor = if (selectedRole == "Admin") Color.White else Color(0xFF3ABABE)
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text("Admin")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Display selected screen
        if (selectedRole == "Volunteer") {
            VolunteerScreen()
        } else {
            AdminScreen()
        }
    }
}

@Composable
fun VolunteerScreen() {
    var fullName by remember { mutableStateOf("") }
    var nationality by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.logo_3ababe),
            contentDescription = "Logo",
            modifier = Modifier.size(160.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Join as Volunteer", color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Fill in your details to start volunteering",
            color = Color.Black,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = nationality,
            onValueChange = { nationality = it },
            label = { Text("Nationality") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Handle volunteer sign up */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White)
        ) {
            Text("Sign Up")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "By clicking Sign Up, you agree to our Terms of Service and Privacy Policy",
            color = Color.Gray,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun AdminScreen() {
    var email by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.logo_3ababe),
            contentDescription = "Logo",
            modifier = Modifier.size(160.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Create an account", color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Enter your email to sign up for this app", color = Color.Black, fontSize = 14.sp, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("example@email.com", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* Handle admin continue */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White)
        ) {
            Text("Continue")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("-------------------------------- or --------------------------------", color = Color.Gray, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { /* Handle Google sign in */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2F2F2), contentColor = Color.Black)
            ) {
                Image(painter = painterResource(id = R.drawable.ic_google), contentDescription = "Google", modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Continue with Google")
            }

            Button(
                onClick = { /* Handle Apple sign in */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2F2F2), contentColor = Color.Black)
            ) {
                Image(painter = painterResource(id = R.drawable.ic_apple), contentDescription = "Apple", modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Continue with Apple")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "By clicking continue, you agree to our Terms of Service and Privacy Policy",
            color = Color.Gray,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    ICT602MY_VOLTheme {
        MainScreen()
    }
}
