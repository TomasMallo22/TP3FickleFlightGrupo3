package com.example.tp3fickleflightgrupo3.ui.offers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp3fickleflightgrupo3.data.model.Offer
import com.example.tp3fickleflightgrupo3.provider.OffersProvider

class OfferViewModel : ViewModel() {

    private val _offers = MutableLiveData<List<Offer>>().apply {
        value = OffersProvider.offerList
        Log.d("ExploreViewModel", "Offers Loaded: ${OffersProvider.offerList}")
    }
    val offers: LiveData<List<Offer>> = _offers
}
