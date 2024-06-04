package com.example.tp3fickleflightgrupo3.holders

import Flight
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp3fickleflightgrupo3.R
import com.google.android.material.button.MaterialButton

class FlightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val airlineLogo: ImageView = itemView.findViewById(R.id.airlineLogo)
    private val airlineName: TextView = itemView.findViewById(R.id.airlineName)
    private val durationIcon: ImageView = itemView.findViewById(R.id.durationIcon)
    private val flightDuration: TextView = itemView.findViewById(R.id.flightDuration)
    private val departureAirportId: TextView = itemView.findViewById(R.id.departureAirportId)
    private val departureAirport: TextView = itemView.findViewById(R.id.departureAirport)
    private val flightIcon: ImageView = itemView.findViewById(R.id.flightIcon)
    private val arrivalAirportId: TextView = itemView.findViewById(R.id.arrivalAirportId)
    private val arrivalAirport: TextView = itemView.findViewById(R.id.arrivalAirport)
    private val flightClass: TextView = itemView.findViewById(R.id.flightClass)
    private val flightPrice: TextView = itemView.findViewById(R.id.flightPrice)
    private val btnViewDetails: MaterialButton = itemView.findViewById(R.id.btnViewDetails)

    fun bind(flight: Flight) {
        Glide.with(itemView.context).load(flight.airline_logo).into(airlineLogo)
        airlineName.text = flight.airline
        flightDuration.text = "Duration: ${flight.duration} mins"
        departureAirportId.text = flight.departure_airport.id
        departureAirport.text = flight.departure_airport.name
        arrivalAirportId.text = flight.arrival_airport.id
        arrivalAirport.text = flight.arrival_airport.name
        flightClass.text = flight.travel_class
        flightPrice.text = "From $${flight.price}"
        Glide.with(itemView.context).load(R.drawable.ic_flight).into(flightIcon)
    }
}
