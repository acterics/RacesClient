package com.acterics.racesclient.ui.schedule

/**
 * Created by root on 15.10.17.
 */

import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.acterics.racesclient.R
import com.acterics.racesclient.data.entity.Race
import com.acterics.racesclient.utils.Formats
import com.acterics.racesclient.utils.formattedDate
import com.acterics.racesclient.utils.setSupportTranslationName
import com.acterics.racesclient.utils.suffixedFormattedDate
import com.mikepenz.fastadapter.items.AbstractItem

class ScheduleItem(val race: Race) : AbstractItem<ScheduleItem, ScheduleItem.ScheduleItemViewHolder>(), Parcelable {
    val holderTranslationName = "${race.title} holder"
    val titleTranslationName = "${race.title} title"
    val organizerTranslationName = "${race.title} organizer"

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

        holder.itemView.setSupportTranslationName(holderTranslationName)
        holder.tvRaceTitle.setSupportTranslationName(titleTranslationName)
        holder.tvRaceOrganizer.setSupportTranslationName(organizerTranslationName)

    }

    class ScheduleItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRaceDate: TextView by lazy { itemView.findViewById<TextView>(R.id.tvRaceDate) }
        val tvRaceTime: TextView by lazy { itemView.findViewById<TextView>(R.id.tvRaceTime) }
        val tvRaceOrganizer: TextView by lazy { itemView.findViewById<TextView>(R.id.tvRaceOrganizer) }
        val tvRaceTitle: TextView by lazy { itemView.findViewById<TextView>(R.id.tvRaceTitle) }

    }

    constructor(source: Parcel) : this(
            source.readParcelable<Race>(Race::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(race, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ScheduleItem> = object : Parcelable.Creator<ScheduleItem> {
            override fun createFromParcel(source: Parcel): ScheduleItem = ScheduleItem(source)
            override fun newArray(size: Int): Array<ScheduleItem?> = arrayOfNulls(size)
        }
    }
}