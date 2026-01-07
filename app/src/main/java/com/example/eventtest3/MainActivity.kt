package com.example.eventtest3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.eventtest3.PrimaryBackground
import com.example.eventtest3.ui.theme.EventTest3Theme
import com.example.eventtest3.OrganizersScreen // Import OrganizersScreen
import com.example.eventtest3.Organizer
import com.example.eventtest3.EventsScreen



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
@Composable
fun HomeScreen(padding: PaddingValues) {
    val homeNavController = rememberNavController()

    val organizers = listOf(
        Organizer("Amanah.Co", R.drawable.company_logo),
        Organizer("MyHelper", R.drawable.company_logo),
        Organizer("Runner", R.drawable.company_logo),
        Organizer("Seaboree", R.drawable.company_logo),
        Organizer("Event Organizer", R.drawable.company_logo),
        Organizer("Scout", R.drawable.company_logo),
        Organizer("Eventify", R.drawable.company_logo),
        Organizer("CrewWorks", R.drawable.company_logo),
        Organizer("UniEvents", R.drawable.company_logo),
        Organizer("Voluntrix", R.drawable.company_logo),
        Organizer("Campus Hub", R.drawable.company_logo),
        Organizer("Youth Connect", R.drawable.company_logo),
        Organizer("ProActive", R.drawable.company_logo),
        Organizer("NextGen Events", R.drawable.company_logo),
    )

    val events = listOf(
        Event("Amanah.Co", "Save Turtle", "12 Jan 2026", "Ipoh", R.drawable.location_pic),
        Event("MyHelper", "Help Homeless", "12 Jan 2026", "KL",  R.drawable.location_pic),
        Event("Runner", "Hari Menanam Pokok", "20 Feb 2026", "Genting", R.drawable.location_pic),
        Event("Event Organizer", "Project 2026", "5 Mac 2026", "Penang", R.drawable.location_pic),
        Event("Scout", "Health Awareness", "1 April 2026", "JB", R.drawable.location_pic),
        Event("Event Organizer", "Help Homeless", "15 Mei 2026", "Cyberjaya", R.drawable.location_pic),
        Event("Seaboree", "Beach Cleaning", "1 Jun 2026", "Muar", R.drawable.location_pic),
        Event("Scout", "Bubur Lambuk", "10 Julai 2026", "Subang", R.drawable.location_pic)
    )

    NavHost(
        navController = homeNavController,
        startDestination = "event_list_screen",
        modifier = Modifier.padding(padding) // Penting untuk Padding Bottom Bar
    ) {

        // --- ROUTE 1: SKRIN SENARAI ACARA ---
        composable("event_list_screen") {
            var searchState by remember { mutableStateOf(TextFieldValue("")) }

            val filteredEvents = events.filter {
                it.name.contains(searchState.text, ignoreCase = true)
            }

            // LazyColumn kini menggunakan searchState dan filteredEvents
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {

                // Home Interface
                // Item 1: Tajuk
                item { HeaderTitle("Event list") }

                // Item 2: Bar Carian
                item { SearchBar(searchState) { searchState = it } }

                // Item 3: Bahagian Penganjur
                item {
                    OrganizerSection(
                        organizers = organizers,
                        onViewAllClick = {
                            homeNavController.navigate("organizers_screen")
                        }
                    )
                }

                // Item 4: Tajuk untuk senarai acara
                item {
                    EventHeader() {
                        homeNavController.navigate("events_screen")
                    }
                }


                    // Item 5: Senarai Acara (Event) yang boleh diklik
                    items(filteredEvents.withIndex().toList()) { (index, event) ->
                        EventCard(event) {
                            // Navigasi ke skrin butiran dengan menghantar index asal
                            homeNavController.navigate("event_details_screen/$index")
                        }


                    }

            }

        }


        // --- ROUTE 2: SKRIN DETAIL ACARA (Kekal Sama) ---
        composable(
            route = "event_details_screen/{eventId}",
            arguments = listOf(navArgument("eventId") { type = NavType.IntType })
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt("eventId") ?: 0
            val selectedEvent = events.getOrNull(eventId) ?: events.first()

            EventDetailsScreen(
                event = selectedEvent,
                onViewAllOrganizersClick = {
                    homeNavController.navigate("organizers_screen")
                },
                onBackClick = {
                    homeNavController.popBackStack()
                }
            )
        }

        // --- ROUTE 3: SKRIN ORGANIZERS (Kekal Sama) ---
        composable("organizers_screen") {
            OrganizersScreen(
                onBackClick = {
                    homeNavController.popBackStack()
                },
                organizers = organizers
            )
        }

        // --- ROUTE 4: EVENT SCREEN  ---
        composable("events_screen") {
            EventsScreen(
                onBackClick = {
                    homeNavController.popBackStack()
                },
                events = events, // Hantar senarai event
                onEventClick = { clickedEvent ->
                    // Cari index event yang diklik untuk navigasi ke details screen
                    val index = events.indexOf(clickedEvent)
                    if (index != -1) {
                        homeNavController.navigate("event_details_screen/$index")
                    }
                }
            )
        }
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
fun EventHeader(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable(onClick = onClick),
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
fun EventCard(event: Event, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
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

// ===================== PLACEHOLDER: EVENT DETAILS SCREEN =====================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailsScreen(
    event: Event,
    onViewAllOrganizersClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(event.name, color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)

            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            item {
                Text(
                    "Ini adalah detail untuk event: ${event.name}",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                // === SEKSYEN ORGANIZER (Tempat Butang Arrow Anda) ===
                // Kita panggil header yang sama dan berikan fungsi navigasi
                OrganizerSectionHeader(
                    onViewAllClick = onViewAllOrganizersClick // Ini akan navigasi ke OrganizersScreen
                )

                Text(
                    "Organizer: ${event.organizer}",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}
// ======================================================================

