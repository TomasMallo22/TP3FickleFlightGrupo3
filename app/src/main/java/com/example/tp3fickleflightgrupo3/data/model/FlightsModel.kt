package com.example.tp3fickleflightgrupo3.data.model

data class FlightsModel(
    val departure_airport: Airport,
    val arrival_airport: Airport,
    val duration: Int,
    val airplane: String,
    val airline: String,
    val airline_logo: String,
    val travel_class: String,
    val flight_number: String,
    val legroom: String,
    val extensions: List<String>,
    val overnight: Boolean = false,
    val often_delayed_by_over_30_min: Boolean = false
)

data class Airport(
    val name: String,
    val id: String,
    val time: String
)