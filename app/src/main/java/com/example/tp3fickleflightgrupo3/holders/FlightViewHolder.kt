package com.example.tp3fickleflightgrupo3.holders

import Flight
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp3fickleflightgrupo3.R

class FlightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val airlineLogo: ImageView = itemView.findViewById(R.id.airlineLogo)
    private val airlineName: TextView = itemView.findViewById(R.id.airlineName)
    private val departureAirport: TextView = itemView.findViewById(R.id.departureAirport)
    private val arrivalAirport: TextView = itemView.findViewById(R.id.arrivalAirport)
    private val flightDuration: TextView = itemView.findViewById(R.id.flightDuration)
    private val flightNumber: TextView = itemView.findViewById(R.id.flightPrice)

    fun bind(flight: Flight) {
        Glide.with(itemView.context).load(flight.airline_logo).into(airlineLogo)
        airlineName.text = flight.airline
        departureAirport.text = flight.departure_airport.name
        arrivalAirport.text = flight.arrival_airport.name
        flightDuration.text = "Duration: ${flight.duration} mins"
        flightNumber.text = "Flight No: ${flight.flight_number}"
    }
}