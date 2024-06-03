package com.example.tp3fickleflightgrupo3.adapters

import Flight
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.tp3fickleflightgrupo3.R
import com.example.tp3fickleflightgrupo3.holders.FlightViewHolder

class FlightAdapter : ListAdapter<Flight, FlightViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flight, parent, false)
        return FlightViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val flight = getItem(position)
        holder.bind(flight)
    }

    class DiffCallback : DiffUtil.ItemCallback<Flight>() {
        override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean {
            return oldItem.flight_number == newItem.flight_number
        }

        override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean {
            return oldItem == newItem
        }
    }
}
