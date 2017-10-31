package com.acterics.racesclient.presentation.racedetails

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.acterics.racesclient.R

/**
 * Created by root on 31.10.17.
 */
class BetHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvBet: TextView by lazy { itemView.findViewById<TextView>(R.id.tvBet) }
    val tvAward: TextView by lazy { itemView.findViewById<TextView>(R.id.tvAward) }
}