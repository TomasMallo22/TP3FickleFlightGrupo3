package com.example.tp3fickleflightgrupo3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3fickleflightgrupo3.R
import com.example.tp3fickleflightgrupo3.data.model.Offer
import com.example.tp3fickleflightgrupo3.holders.OfferExploreHolder

class OfferExploreAdapter(
    private var offers: List<Offer>,
    private val onItemClick: (Offer) -> Unit
) :
    RecyclerView.Adapter<OfferExploreHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferExploreHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_offer_explore, parent, false)
        return OfferExploreHolder(view)
    }

    override fun onBindViewHolder(holder: OfferExploreHolder, position: Int) {
        val offer = offers[position]
        holder.bind(offer)
        holder.itemView.setOnClickListener {
            onItemClick(offer)
        }
    }

    override fun getItemCount(): Int = offers.size

    fun updateOffers(newOffers: List<Offer>) {
        offers = newOffers
    }
}
