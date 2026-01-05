package com.example.eventtest3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventtest3.ui.theme.EventTest3Theme

// ===================== DATA MODEL =====================
data class Organizer(val title: String)

data class Event(
    val organizer: String,
    val name: String,
    val date: String,
    val location: String
)

// ===================== MAIN ACTIVITY =====================
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventTest3Theme {
                MainScreen()
            }
        }
    }
}

// ===================== MAIN SCREEN =====================
@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(selectedTab) { selectedTab = it }
        }
    ) { padding ->
        when (selectedTab) {
            0 -> HomeScreen(padding)
            1 -> NotificationScreen(padding)
            2 -> ProfileScreen(padding)
        }
    }
}

// ===================== HOME SCREEN =====================
@Composable
fun HomeScreen(padding: PaddingValues) {
    var search by remember { mutableStateOf("") }

    val organizers = listOf(
        Organizer("Food"),
        Organizer("Sport"),
        Organizer("Nature"),
        Organizer("Art")
    )

    val events = listOf(
        Event("Organizer", "Event Name", "12 Jan 2026", "Ipoh"),
        Event("Organizer", "Event Name", "15 Jan 2026", "KL"),
        Event("Organizer", "Event Name", "20 Jan 2026", "Penang")
    )

    val filteredEvents = events.filter {
        it.name.contains(search, ignoreCase = true)
    }

    LazyColumn(
        contentPadding = padding,
        modifier = Modifier.fillMaxSize()
    ) {
        item { SearchBar(search) { search = it } }
        item { OrganizerSection(organizers) }
        items(filteredEvents) { EventCard(it) }
    }
}

// ===================== SEARCH =====================
@Composable
fun SearchBar(text: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        placeholder = { Text("Search") },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    )
}

// ===================== ORGANIZER =====================
@Composable
fun OrganizerSection(organizers: List<Organizer>) {
    Text(
        text = "Organizers",
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 16.dp)
    )

    LazyRow(
        modifier = Modifier.padding(8.dp)
    ) {
        items(organizers) { OrganizerItem(it) }
    }
}

@Composable
fun OrganizerItem(organizer: Organizer) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )
        Text(organizer.title, fontSize = 12.sp)
    }
}

// ===================== EVENT =====================
@Composable
fun EventCard(event: Event) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { }
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )

            Column(
                modifier = Modifier
                    .background(Color(0xFF36B8B1))
                    .padding(12.dp)
            ) {
                Text(event.organizer, color = Color.White, fontSize = 12.sp)
                Text(event.name, color = Color.White, fontWeight = FontWeight.Bold)
                Text(event.date, color = Color.White, fontSize = 12.sp)
                Text(event.location, color = Color.White, fontSize = 12.sp)
            }
        }
    }
}

// ===================== BOTTOM NAV =====================
@Composable
fun BottomNavigationBar(selected: Int, onSelect: (Int) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            selected = selected == 0,
            onClick = { onSelect(0) },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = selected == 1,
            onClick = { onSelect(1) },
            icon = { Icon(Icons.Default.Notifications, null) },
            label = { Text("Notification") }
        )
        NavigationBarItem(
            selected = selected == 2,
            onClick = { onSelect(2) },
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text("Profile") }
        )
    }
}

// ===================== OTHER SCREENS =====================
@Composable
fun NotificationScreen(padding: PaddingValues) {
    Box(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Notification Screen")
    }
}

@Composable
fun ProfileScreen(padding: PaddingValues) {
    Box(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Profile Screen")
    }
}

// ===================== PREVIEW =====================
@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    EventTest3Theme {
        MainScreen()
    }
}

