package com.example.tp3fickleflightgrupo3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3fickleflightgrupo3.R
import com.example.tp3fickleflightgrupo3.data.model.Offer
import com.example.tp3fickleflightgrupo3.holders.OfferHolder

class OfferAdapter(
    private var offers: List<Offer>
) :
    RecyclerView.Adapter<OfferHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_offer, parent, false)
        return OfferHolder(view)
    }

    override fun onBindViewHolder(holder: OfferHolder, position: Int) {
        val offer = offers[position]
        holder.bind(offer)
    }

    override fun getItemCount(): Int = offers.size

    fun updateOffers(newOffers: List<Offer>) {
        offers = newOffers
    }
}
