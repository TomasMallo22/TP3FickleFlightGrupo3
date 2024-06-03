package com.example.tp3fickleflightgrupo3.service

import FlightsResponse
import retrofit2.Call
import retrofit2.http.GET

interface FlightsService {
    @GET("/search?engine=google_flights&api_key=123&departure_id=EZE&arrival_id=MIA&outbound_date=2024-05-31&return_date=2024-06-10")
    fun getFlights(): Call<FlightsResponse>
}
