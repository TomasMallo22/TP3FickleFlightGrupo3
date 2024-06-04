package com.example.tp3fickleflightgrupo3.provider

import com.example.tp3fickleflightgrupo3.R
import com.example.tp3fickleflightgrupo3.data.model.Destination

class TrendingDestinationsProvider {
    companion object {
        val trendingDestinationList = listOf<Destination>(
            Destination(R.drawable.boracay, "Boracay", "Philippines", "5D4N"),
            Destination(R.drawable.baros, "Baros", "Maldives", "7D6N"),
            Destination(R.drawable.bali, "Bali", "Indonesia", "3D2N"),
            Destination(R.drawable.palawan, "Palawan", "Philippines", "3D2N"),
        )
    }
}
