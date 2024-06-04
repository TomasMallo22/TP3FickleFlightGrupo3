package com.example.tp3fickleflightgrupo3.ui.flightSearch

import Flight
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp3fickleflightgrupo3.data.model.Offer
import com.example.tp3fickleflightgrupo3.provider.OffersProvider
import com.example.tp3fickleflightgrupo3.service.FlightsApi
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class FlightSearchViewModel : ViewModel() {

    private val _flights = MutableLiveData<List<Flight>>()
    val flights: LiveData<List<Flight>> get() = _flights

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _offers = MutableLiveData<List<Offer>>().apply {
        value = OffersProvider.offerList
        Log.d("ExploreViewModel", "Offers Loaded: ${OffersProvider.offerList}")
    }
    val offers: LiveData<List<Offer>> = _offers

    fun searchFlights() {
        viewModelScope.launch {
            try {
                Log.d("FlightSearchViewModel", "searchFlights called")
                val response = FlightsApi.create().getFlights().awaitResponse()
                Log.d("FlightSearchViewModel", "Response received: $response")
                if (response.isSuccessful) {
                    val flightsResponse = response.body()
                    Log.d("FlightSearchViewModel", "FlightsResponse: $flightsResponse")
                    flightsResponse?.best_flights?.let { bestFlights ->
                        if (bestFlights.isNotEmpty()) {
                            val flights = bestFlights.flatMap { it.flights ?: emptyList() }
                            _flights.postValue(flights)
                        } else {
                            _error.postValue("Failed to retrieve flights")
                        }
                    } ?: run {
                        _error.postValue("Failed to retrieve flights")
                    }
                } else {
                    _error.postValue("Failed to retrieve flights")
                }
            } catch (e: Exception) {
                Log.e("FlightSearchViewModel", "Error: ${e.message}", e)
                _error.postValue(e.message)
            }
        }
    }
}
