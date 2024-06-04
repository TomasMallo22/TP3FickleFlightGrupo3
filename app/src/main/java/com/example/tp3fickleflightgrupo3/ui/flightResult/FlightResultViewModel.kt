package com.example.tp3fickleflightgrupo3.ui.flightResult

import Flight
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp3fickleflightgrupo3.service.FlightsApi
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class FlightResultViewModel : ViewModel() {
    private val _flights = MutableLiveData<List<Flight>?>()
    val flights: MutableLiveData<List<Flight>?> get() = _flights

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun searchFlights() {
        viewModelScope.launch {
            try {
                val response = FlightsApi.create().getFlights().awaitResponse()
                if (response.isSuccessful) {
                    val flightsResponse = response.body()
                    val flightsList =
                        flightsResponse?.best_flights?.flatMap { it.flights ?: emptyList() }
                    _flights.postValue(flightsList)
                } else {
                    _error.postValue("No se pudieron recuperar los vuelos")
                }
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }
}
