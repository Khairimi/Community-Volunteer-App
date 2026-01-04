import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.volunteerfigma.R // GANTIKAN ini dengan nama pakej projek anda

class EventAdapter(private val eventList: List<Event>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.event_image)
        val organizer: TextView = itemView.findViewById(R.id.text_organizer)
        val name: TextView = itemView.findViewById(R.id.event_name)
        val date: TextView = itemView.findViewById(R.id.text_date)
        val location: TextView = itemView.findViewById(R.id.text_location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event_card, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]
        holder.organizer.text = event.organizer
        holder.name.text = event.name
        holder.date.text = event.date
        holder.location.text = event.location
        holder.image.setImageResource(event.imageResId)
    }

    override fun getItemCount() = eventList.size
}