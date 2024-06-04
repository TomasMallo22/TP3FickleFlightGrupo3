package com.example.tp3fickleflightgrupo3.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp3fickleflightgrupo3.R
import com.example.tp3fickleflightgrupo3.data.model.Offer

class OfferExploreHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.offer_image)
    private val descriptionShort: TextView = view.findViewById(R.id.offer_description_short)

    fun bind(offer: Offer) {
        descriptionShort.text = offer.descriptionShort
        Glide.with(image.context).load(offer.imageResId).into(image)
    }
}
