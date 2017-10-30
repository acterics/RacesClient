package com.acterics.racesclient.presentation.racedetails

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.acterics.racesclient.R

/**
 * Created by root on 30.10.17.
 */
class ParticipationExpandedHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val etAward: EditText by lazy { itemView.findViewById<EditText>(R.id.etAward) }
    val etBet: EditText by lazy { itemView.findViewById<EditText>(R.id.etBet) }
    val btConfirm: Button by lazy { itemView.findViewById<Button>(R.id.btConfirm) }
    val btClear: Button by lazy { itemView.findViewById<Button>(R.id.btClear) }

}