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
import androidx.compose.foundation.text.BasicTextField // Diperlukan untuk SearchBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight // Diperlukan untuk ikon arrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventtest3.ui.theme.EventTest3Theme

// ===================== CUSTOM COLORS =====================
val PrimaryBackground = Color(0xFF36B8B1) // Hijau/Biru untuk Organizers dan Event Card
val ArrowColor = Color(0xFF8B8B8B) // Warna Kelabu untuk ikon arrow di Event Header

// ===================== DATA MODEL =====================
data class Organizer(val title: String, val imageResId: Int)
data class Event(val organizer: String, val name: String, val date: String, val location: String, val imageResId: Int)

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
// ===================== HOME SCREEN =====================
@Composable
fun HomeScreen(padding: PaddingValues) {
    var searchState by remember { mutableStateOf(TextFieldValue("")) }

    val organizers = listOf(
        Organizer("Amanah.Co", R.drawable.ic_launcher_foreground),
        Organizer("Yatim Helper", R.drawable.ic_launcher_foreground),
        Organizer("Runner", R.drawable.ic_launcher_foreground),
        Organizer("Seaboree", R.drawable.ic_launcher_foreground),
        Organizer("Event Organizer", R.drawable.ic_launcher_foreground),
        Organizer("Scout", R.drawable.ic_launcher_foreground),
    )

    // TAMBAH LEBIH BANYAK EVENT DI SINI (CONTOH: 8-10 EVENT)
    val events = listOf(
        Event("Organizer 1", "Selamatkan Penyu", "12 Jan 2026", "Ipoh", R.drawable.ic_launcher_background),
        Event("Organizer 2", "Alam Sekitar 2026", "15 Jan 2026", "KL", R.drawable.ic_launcher_background),
        Event("Organizer 3", "Hari Menanam Pokok", "20 Feb 2026", "Genting", R.drawable.ic_launcher_background),
        Event("Organizer 4", "Art Expo", "5 Mac 2026", "Penang", R.drawable.ic_launcher_background),
        Event("Organizer 5", "Music Concert", "1 April 2026", "JB", R.drawable.ic_launcher_background),
        Event("Organizer 6", "Tech Summit", "15 Mei 2026", "Cyberjaya", R.drawable.ic_launcher_background),
        Event("Organizer 7", "Gaming Expo", "1 Jun 2026", "Muar", R.drawable.ic_launcher_background),
        Event("Organizer 8", "Car Meetup", "10 Julai 2026", "Subang", R.drawable.ic_launcher_background)
        // Jika perlu, tambah lagi event di sini
    )
    // ... baki kod

    val filteredEvents = events.filter {
        it.name.contains(searchState.text, ignoreCase = true)
    }

    LazyColumn(
        contentPadding = padding,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item { HeaderTitle("Event list") }
        item { SearchBar(searchState) { searchState = it } }
        // Gunakan OrganizerSection yang telah dipecahkan (dengan fungsi kosong untuk onClick)
        item { OrganizerSection(organizers = organizers, onViewAllClick = {}) }
        item { EventHeader() }
        items(filteredEvents) { EventCard(it) }
    }
}

// ===================== HEADER TITLE =====================
@Composable
fun HeaderTitle(title: String) {
    Text(
        text = title,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
    )
}

// ===================== SEARCH =====================
@Composable
fun SearchBar(textState: TextFieldValue, onTextChange: (TextFieldValue) -> Unit) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFF0F0F0),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(48.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
            Spacer(Modifier.width(8.dp))
            BasicTextField(
                value = textState,
                onValueChange = onTextChange,
                modifier = Modifier.fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(fontSize = 16.sp, color = Color.Black),
                singleLine = true,
                decorationBox = { innerTextField ->
                    Box(contentAlignment = Alignment.CenterStart) {
                        if (textState.text.isEmpty()) {
                            Text("Search", color = Color.Gray, fontSize = 16.sp)
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}

// ===================== ORGANIZER SECTION (KOMPONEN DIPISAHKAN) =====================

@Composable
fun OrganizerSection(
    organizers: List<Organizer>,
    onViewAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(PrimaryBackground)
    ) {
        OrganizerSectionHeader(onViewAllClick = onViewAllClick)

        OrganizerList(organizers = organizers)
    }
}

@Composable
private fun OrganizerSectionHeader(onViewAllClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Organizers",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "View all",
            tint = Color.White,
            modifier = Modifier.clickable(onClick = onViewAllClick)
        )
    }
}

@Composable
private fun OrganizerList(organizers: List<Organizer>) {
    // ... inside OrganizerList composable
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        // KOD YANG DIPERBAIKI: Menggunakan start, end, dan bottom secara eksplisit
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
//...

        items(
            items = organizers
        ) { organizer ->
            OrganizerItem(organizer)
        }
    }
}

@Composable
fun OrganizerItem(organizer: Organizer) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .clickable { /* Handle organizer click */ }
    ) {
        Image(
            painter = painterResource(id = organizer.imageResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(Color.White)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            organizer.title,
            fontSize = 12.sp,
            maxLines = 1,
            color = Color.White
        )
    }
}

// ===================== EVENT HEADER =====================
@Composable
fun EventHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Event", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "View all events", tint = ArrowColor)
    }
}

// ===================== EVENT CARD =====================
// Pastikan anda mempunyai PrimaryBackground, Event, Icons.Default.DateRange, Icons.Default.LocationOn
// ditakrifkan dalam fail anda.

@Composable
fun EventCard(event: Event) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { /* Handle click to event detail */ }
    ) {
        Column {
            // 1. Event Image Section (Kekal sama)
            Image(
                painter = painterResource(id = event.imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp) // Kekalkan ketinggian imej
                    // Clip hanya bahagian atas kad
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )

            // 2. Info Block Section (Bahagian ini diubah sepenuhnya)
            // Blok ini tidak lagi bertindih, ia berada di bawah imej dan menggunakan PrimaryBackground
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        PrimaryBackground,
                        // Hanya sudut bawah yang perlu dibulatkan
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    )
                    .padding(12.dp) // Padding seragam di dalam blok
            ) {
                // Semua teks kini menggunakan Color.White

                // Organizer
                Text(event.organizer, color = Color.White, fontSize = 12.sp)
                Spacer(Modifier.height(4.dp))

                // Event Name
                Text(event.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(Modifier.height(8.dp))

                // Date and Location row
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Ikon dan Teks Date
                    Icon(Icons.Default.DateRange, contentDescription = "Date", modifier = Modifier.size(16.dp), tint = Color.White)
                    Spacer(Modifier.width(4.dp))
                    Text(event.date, color = Color.White, fontSize = 12.sp)

                    Spacer(Modifier.width(16.dp))

                    // Ikon dan Teks Location
                    Icon(Icons.Default.LocationOn, contentDescription = "Location", modifier = Modifier.size(16.dp), tint = Color.White)
                    Spacer(Modifier.width(4.dp))
                    Text(event.location, color = Color.White, fontSize = 12.sp)
                }
            }
        }
    }
}

// ===================== BOTTOM NAV =====================
@Composable
fun BottomNavigationBar(selected: Int, onSelect: (Int) -> Unit) {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            selected = selected == 0,
            onClick = { onSelect(0) },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Spacer(Modifier.size(0.dp)) },
            alwaysShowLabel = false
        )
        NavigationBarItem(
            selected = selected == 1,
            onClick = { onSelect(1) },
            icon = { Icon(Icons.Default.Notifications, null) },
            label = { Spacer(Modifier.size(0.dp)) },
            alwaysShowLabel = false
        )
        NavigationBarItem(
            selected = selected == 2,
            onClick = { onSelect(2) },
            icon = { Icon(Icons.Default.Person, null) },
            label = { Spacer(Modifier.size(0.dp)) },
            alwaysShowLabel = false
        )
    }
}

// ===================== OTHER SCREENS =====================
@Composable
fun NotificationScreen(padding: PaddingValues) {
    Box(modifier = Modifier
        .padding(padding)
        .fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Notification Screen")
    }
}

@Composable
fun ProfileScreen(padding: PaddingValues) {
    Box(modifier = Modifier
        .padding(padding)
        .fillMaxSize(), contentAlignment = Alignment.Center) {
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