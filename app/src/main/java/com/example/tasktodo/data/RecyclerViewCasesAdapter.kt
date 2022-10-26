package com.example.tasktodo.data

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktodo.R

class RecyclerViewEventAdapter(private var context: Context, private var events: ArrayList<Event>)
    : RecyclerView.Adapter<RecyclerViewEventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
       return EventViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_view_events,
           parent, false))
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentEvent = events[position]
        holder.nameEvent.text = currentEvent.name
        holder.buttonDelete.setOnClickListener {
            deleteEvent(currentEvent, position)
        }
        holder.imageEvent.setImageURI(Uri.fromFile(currentEvent.image))
    }

    override fun getItemCount(): Int {
        return events.size
    }

    private fun deleteEvent(event: Event, position: Int){
        events.remove(event)
        notifyItemRemoved(position)
    }

    inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val buttonDelete: ImageButton = view.findViewById(R.id.btnDelete)
        val imageEvent: ImageView = view.findViewById(R.id.imageEvent)
        val nameEvent : TextView = view.findViewById(R.id.nameOfEvent)

    }

}