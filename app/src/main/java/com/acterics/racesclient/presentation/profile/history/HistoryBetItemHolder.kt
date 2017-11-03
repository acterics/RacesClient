package com.acterics.racesclient.presentation.profile.history

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.acterics.racesclient.R

/**
 * Created by root on 03.11.17.
 */
class HistoryBetItemHolder(view: View): RecyclerView.ViewHolder(view) {
    val tvDate: TextView by lazy { itemView.findViewById<TextView>(R.id.tvDate) }
    val tvBet: TextView by lazy { itemView.findViewById<TextView>(R.id.tvBet) }
    val tvHorseName: TextView by lazy { itemView.findViewById<TextView>(R.id.tvHorseName) }
    val tvResult: TextView by lazy { itemView.findViewById<TextView>(R.id.tvResult) }
    val imResult: ImageView by lazy { itemView.findViewById<ImageView>(R.id.imResult) }
}