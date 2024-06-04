package com.example.tp3fickleflightgrupo3.ui.flightDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import Flight
import com.example.tp3fickleflightgrupo3.service.FlightsApi
import kotlinx.coroutines.launch
import retrofit2.await

class FlightDetailViewModel : ViewModel() {
    private val _flightDetails = MutableLiveData<Flight>()
    val flightDetails: LiveData<Flight> get() = _flightDetails

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        fetchFlightDetails()
    }

    fun fetchFlightDetails() {
        viewModelScope.launch {
            try {
                val flightService = FlightsApi.create()
                val response = flightService.getFlights().await()
                if (response.best_flights?.isNotEmpty() == true) {
                    _flightDetails.postValue(response.best_flights[0].flights?.get(0))
                } else {
                    _error.postValue("No flight details available")
                }
            } catch (e: Exception) {
                _error.postValue("Failed to fetch flight details: ${e.message}")
            }
        }
    }

    fun setFlightDetails(flight: Flight) {
        _flightDetails.value = flight
    }

    fun setError(errorMessage: String) {
        _error.value = errorMessage
    }

}