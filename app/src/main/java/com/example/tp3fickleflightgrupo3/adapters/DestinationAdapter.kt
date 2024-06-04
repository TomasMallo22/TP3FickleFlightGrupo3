package com.example.tp3fickleflightgrupo3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3fickleflightgrupo3.R
import com.example.tp3fickleflightgrupo3.data.model.Destination
import com.example.tp3fickleflightgrupo3.holders.DestinationHolder

class DestinationAdapter(private var destinations: List<Destination>) :
    RecyclerView.Adapter<DestinationHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trending_destination, parent, false)
        return DestinationHolder(view)
    }

    override fun onBindViewHolder(holder: DestinationHolder, position: Int) {
        val destination = destinations[position]
        holder.render(destination)
    }

    override fun getItemCount(): Int = destinations.size

    fun updateDestinations(newDestinations: List<Destination>) {
        destinations = newDestinations
        notifyDataSetChanged()
    }
}
