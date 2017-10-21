package com.acterics.racesclient.ui.schedule

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.acterics.racesclient.R

/**
 * Created by root on 21.10.17.
 */
class ScheduleItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvRaceDate: TextView by lazy { itemView.findViewById<TextView>(R.id.tvRaceDate) }
    val tvRaceTime: TextView by lazy { itemView.findViewById<TextView>(R.id.tvRaceTime) }
    val tvRaceOrganizer: TextView by lazy { itemView.findViewById<TextView>(R.id.tvRaceOrganizer) }
    val tvRaceTitle: TextView by lazy { itemView.findViewById<TextView>(R.id.tvRaceTitle) }

}