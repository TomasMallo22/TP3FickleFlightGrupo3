// src/main/java/com/example/tp3fickleflightgrupo3/holders/DestinationViewHolder.kt
package com.example.tp3fickleflightgrupo3.holders

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.ImageView
import com.example.tp3fickleflightgrupo3.R
import com.example.tp3fickleflightgrupo3.data.model.Destination
import com.bumptech.glide.Glide

class DestinationHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.destination_image)
    private val name: TextView = view.findViewById(R.id.destination_name)
    private val country: TextView = view.findViewById(R.id.destination_country)
    private val duration: TextView = view.findViewById(R.id.destination_duration)

    fun render(model: Destination) {
        name.text = model.name
        country.text = model.country
        duration.text = model.duration
        Glide.with(image.context).load(model.imageResId).into(image)
    }
}
