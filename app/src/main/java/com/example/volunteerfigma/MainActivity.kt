import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// GANTIKAN ini dengan nama pakej projek anda
import com.example.volunteerfigma.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sediakan Data Palsu (Mock Data)
        // Guna R.drawable.ic_placeholder_image atau gambar sebenar anda
        val mockEvents = listOf(
            Event("Healthy Co.", "Organic Food Fair", "Dec 12", "Central Park", R.drawable.ic_placeholder_image),
            Event("Tech Hub", "Android Dev Meetup", "Jan 10", "Tech Tower", R.drawable.ic_placeholder_image),
            Event("Nature Lovers", "Weekend Hiking Trip", "Jan 15", "Green Mountain", R.drawable.ic_placeholder_image),
            Event("Art Masters", "Creative Workshop", "Feb 01", "Gallery X", R.drawable.ic_placeholder_image)
        )

        // Setup RecyclerView untuk Senarai Acara (Menegak)
        val eventsRecyclerView: RecyclerView = findViewById(R.id.events_recycler_view)

        // Tetapkan layout manager kepada Vertical
        eventsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Tetapkan adapter
        val eventAdapter = EventAdapter(mockEvents)
        eventsRecyclerView.adapter = eventAdapter

        // Setup RecyclerView untuk Organizers (Mendatar)
        // Kita gunakan LinearLayoutManager.HORIZONTAL untuk scroll ke tepi
        val organizersRecyclerView: RecyclerView = findViewById(R.id.organizers_recycler_view)
        val horizontalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        organizersRecyclerView.layoutManager = horizontalLayoutManager

        // (Anda perlu menambah OrganizersAdapter yang berasingan di sini jika mahu ia berfungsi)
    }
}