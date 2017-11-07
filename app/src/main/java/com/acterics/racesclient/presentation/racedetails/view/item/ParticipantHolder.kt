package com.acterics.racesclient.presentation.racedetails.view.item

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.acterics.racesclient.R

/**
 * Created by root on 21.10.17.
 */
class ParticipantHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvHorseName: TextView by lazy { itemView.findViewById<TextView>(R.id.tvHorseName) }
    val tvHorseRating: TextView by lazy { itemView.findViewById<TextView>(R.id.tvHorseRating) }
    val btBet: ImageButton by lazy { itemView.findViewById<ImageButton>(R.id.btBet) }
    val vHorseIndicator: View by lazy { itemView.findViewById<View>(R.id.vHorseIndicator) }
}