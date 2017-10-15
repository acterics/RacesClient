package com.acterics.racesclient.ui.schedule

/**
 * Created by root on 15.10.17.
 */

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.acterics.racesclient.R
import com.acterics.racesclient.data.entity.Race
import com.acterics.racesclient.utils.Formats
import com.acterics.racesclient.utils.formattedDate
import com.acterics.racesclient.utils.suffixedFormattedDate
import com.mikepenz.fastadapter.items.AbstractItem

class ScheduleItem(private val race: Race): AbstractItem<ScheduleItem, ScheduleItem.ScheduleItemViewHolder>() {



    override fun getViewHolder(v: View): ScheduleItemViewHolder {
        return ScheduleItemViewHolder(v)
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_race
    }

    override fun getType(): Int {
        return R.id.itemRace
    }

    override fun bindView(holder: ScheduleItemViewHolder, payloads: MutableList<Any>?) {
        super.bindView(holder, payloads)

        holder.tvRaceTitle.text = race.title
        holder.tvRaceOrganizer.text = race.organizer.name
        holder.tvRaceDate.text = race.date.suffixedFormattedDate(Formats.SCHEDULE_DATE_FORMAT)
        holder.tvRaceTime.text = race.date.formattedDate(Formats.SCHEDULE_TIME_FORMAT)

    }


    class ScheduleItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvRaceDate: TextView by lazy { itemView.findViewById<TextView>(R.id.tvRaceDate) }
        val tvRaceTime: TextView by lazy { itemView.findViewById<TextView>(R.id.tvRaceTime) }
        val tvRaceOrganizer: TextView by lazy { itemView.findViewById<TextView>(R.id.tvRaceOrganizer) }
        val tvRaceTitle: TextView by lazy { itemView.findViewById<TextView>(R.id.tvRaceTitle) }

    }
}