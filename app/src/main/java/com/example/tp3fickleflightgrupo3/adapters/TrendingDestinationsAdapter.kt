package com.example.tp3fickleflightgrupo3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3fickleflightgrupo3.R
import com.example.tp3fickleflightgrupo3.data.model.Destination
import com.example.tp3fickleflightgrupo3.holders.TrendingDestinationHolder

class TrendingDestinationsAdapter(private var destinations: List<Destination>) :
    RecyclerView.Adapter<TrendingDestinationHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingDestinationHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trending_destination, parent, false)
        return TrendingDestinationHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingDestinationHolder, position: Int) {
        val destination = destinations[position]
        holder.render(destination)
    }

    override fun getItemCount(): Int = destinations.size

    fun updateDestinations(newDestinations: List<Destination>) {
        destinations = newDestinations
        notifyDataSetChanged()
    }
}
