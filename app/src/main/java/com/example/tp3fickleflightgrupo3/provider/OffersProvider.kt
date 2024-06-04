package com.example.tp3fickleflightgrupo3.provider

import com.example.tp3fickleflightgrupo3.R
import com.example.tp3fickleflightgrupo3.data.model.Offer

class OffersProvider {
    companion object {
        val offerList = listOf<Offer>(
            Offer(
                R.drawable.mastercard_card,
                "20% discount for mastercard users",
                "Get 20% discount with your Master credit cards",
                "Use your mastercard with any transaction and get 20% discount instantly!"
            ),
            Offer(
                R.drawable.visa_card,
                "25% discount with your VISA credit cards",
                "25% discount with your VISA credit or debit cards",
                "Use your VISA credit or debit card with any transaction and get 25% discount instantly!"
            )
        )
    }
}