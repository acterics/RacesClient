package com.acterics.racesclient.presentation.racedetails.view.item

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.acterics.racesclient.R

/**
 * Created by root on 07.11.17.
 */
class AddBetHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val btAddBet: Button by lazy { itemView.findViewById<Button>(R.id.btAddBet) }
}