package com.example.tp3fickleflightgrupo3.ui.explore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp3fickleflightgrupo3.data.model.Destination
import com.example.tp3fickleflightgrupo3.data.model.Offer
import com.example.tp3fickleflightgrupo3.provider.OffersProvider
import com.example.tp3fickleflightgrupo3.provider.TrendingDestinationsProvider

class ExploreViewModel : ViewModel() {

    private val _trendingDestinations = MutableLiveData<List<Destination>>().apply {
        value = TrendingDestinationsProvider.trendingDestinationList
        Log.d(
            "ExploreViewModel",
            "Trending Destinations Loaded: ${TrendingDestinationsProvider.trendingDestinationList}"
        )
    }
    val trendingDestinations: LiveData<List<Destination>> = _trendingDestinations

    private val _offers = MutableLiveData<List<Offer>>().apply {
        value = OffersProvider.offerList
        Log.d("ExploreViewModel", "Offers Loaded: ${OffersProvider.offerList}")
    }
    val offers: LiveData<List<Offer>> = _offers
}
