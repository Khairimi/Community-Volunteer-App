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
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun OrganizersScreenPreview() {
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
    OrganizersScreen(
        onBackClick = {
            // Tiada tindakan dalam Preview
        },
        // Guna senarai 'organizers' yang diimport dari MainActivity
        organizers = organizers
    )
}

// ===================== DEFINISI WARNA MENGIKUT NILAI FIGMA BARU =====================
// Latar belakang keseluruhan skrin (Gelap, seperti 0xFF1E2F33)
val ScreenBackground = Color(0xFF1E2F33)
val TopBarColor = Color.White // Putih untuk Top Bar
// Primary Color untuk Item List & Top Bar (Hijau/Biru Terang dari #33AAAD)
val PrimaryDesignColor = Color(0xFF33AAAD)
// Warna untuk Kotak 'Organizers' (Badge) dari #266668
val OrganizerBadgeColor = Color(0xFF266668)

// ===================== 1. SKRIN UTAMA ORGANIZERS =====================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganizersScreen(
    onBackClick: () -> Unit,
    organizers: List<Organizer>
) {
    Scaffold(
        containerColor = ScreenBackground, // Latar belakang utama gelap
        topBar = {
            // TopAppBar standard (Putih)
            TopAppBar(
                title = { Text("Event Details", color = Color.Black, fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = TopBarColor) // Putih
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(PrimaryBackground)
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // ITEM PERTAMA: KOTAK ORGANIZERS (Overlap/Pil)
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        // OFFSET KE ATAS untuk membuat ia kelihatan bertindih di atas Top Bar
                        .offset(y = (10).dp)
                        .padding(bottom = 0.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Kotak Berasingan (Pil)
                    Row(
                        modifier = Modifier
                            // Corner Radius 80dp
                            .clip(RoundedCornerShape(150.dp))
                            .background(OrganizerBadgeColor) // Warna #266668
                            .padding(horizontal = 50.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Logo Aplikasi
                        Image(
                            painter = painterResource(id = R.drawable.company_logo), // Ganti dengan ikon sebenar anda
                            contentDescription = "App Logo",
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Organizers",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            // Senarai Organizer
            items(organizers) { organizer ->
                OrganizerListItem(organizer)
            }
        }
    }
}

// ===================== 2. ITEM SENARAI ORGANIZER (Diselaraskan dengan Figma) =====================
@Composable
fun OrganizerListItem(organizer: Organizer) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                OrganizerBadgeColor,
                shape = RoundedCornerShape(80.dp) // Sudut bulat
            )
            .clickable { /* Handle click to view organizer profile */ }
            .padding(16.dp), // Padding dalam 16dp
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon/Logo Organizer (Saiz 48dp)
        Image(
            painter = painterResource(id = organizer.imageResId),
            contentDescription = "Organizer Icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.White)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = organizer.title,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}