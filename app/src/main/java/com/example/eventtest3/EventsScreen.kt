package com.example.eventtest3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Mengimport warna dari Organizers.kt/MainActivity.kt
// Guna PrimaryBackground dari MainActivity.kt (0xFF36B8B1)
val EventScreenPrimaryColor = Color(0xFF36B8B1)
val EventScreenBadgeColor = Color(0xFF266668) // Warna yang sama digunakan untuk Organizer Badge

@Preview(showBackground = true)
@Composable
fun EventsScreenPreview() {
    val sampleEvents = listOf(
        Event("Amanah.Co", "Save Turtle", "12 Jan 2026", "Ipoh", R.drawable.location_pic),
        Event("MyHelper", "Help Homeless", "12 Jan 2026", "KL",  R.drawable.location_pic),
        Event("Runner", "Hari Menanam Pokok", "20 Feb 2026", "Genting", R.drawable.location_pic),
    )
    EventsScreen(
        onBackClick = {},
        events = sampleEvents,
        onEventClick = {}
    )
}

// ===================== 1. SKRIN UTAMA EVENTS =====================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(
    onBackClick: () -> Unit,
    events: List<Event>,
    onEventClick: (Event) -> Unit
) {
    Scaffold(
        containerColor = Color.White, // Latar belakang utama putih
        topBar = {
            TopAppBar(
                title = { Text("Event Details", color = Color.Black, fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                // Guna warna dari gambar yang diberikan
                .background(EventScreenPrimaryColor)
                .padding(paddingValues),
            // Padding atas/bawah yang lebih besar untuk membezakan
            contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // ITEM PERTAMA: KOTAK EVENTS (Overlap/Pil) - SAMA SEPERTI GAMBAR
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        // OFFSET KE ATAS untuk membuat ia kelihatan bertindih di atas Top Bar
                        .offset(y = (8).dp)
                        .padding(start = 16.dp, end = 16.dp, bottom = 0.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Kotak Berasingan (Pil)
                    Row(
                        modifier = Modifier
                            // Corner Radius 80dp
                            .clip(RoundedCornerShape(150.dp))
                            .background(EventScreenBadgeColor) // Warna #266668
                            .padding(horizontal = 50.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Logo Aplikasi (Seperti dalam gambar)
                        Image(
                            // Guna logo sementara atau ikon yang sesuai
                            painter = painterResource(id = R.drawable.company_logo),
                            contentDescription = "App Logo",
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Events",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            // Senarai Event menggunakan EventCard versi yang diubah suai
            items(events) { event ->
                EventCardExtended(event) { onEventClick(event) }
            }
        }
    }
}

// ===================== 2. KAD ACARA YANG DIPERBAIKI (Event Card Extended) =====================
// Kita gunakan reka bentuk Event Card yang anda sediakan, tetapi dengan padding yang berbeza
// untuk kelihatan lebih besar dalam skrin EventsScreen ini.
@Composable
fun EventCardExtended(event: Event, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
        modifier = Modifier
            // Padding sisi 16.dp telah ditetapkan dalam LazyColumn contentPadding.
            // Di sini kita hanya menambah padding menegak jika perlu, tetapi biarkan ContentPadding LazyColumn yang menguruskan.
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clickable(onClick = onClick)
    ) {
        Column {
            // 1. Event Image Section
            Image(
                painter = painterResource(id = event.imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )

            // 2. Info Block Section (Latar Belakang Biru/Hijau)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        OrganizerBadgeColor, // Warna Latar Belakang
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    )
                    .padding(12.dp)
            ) {
                // Semua teks menggunakan Color.White

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

