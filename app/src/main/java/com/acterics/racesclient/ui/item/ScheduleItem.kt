package com.acterics.racesclient.ui.item

import android.os.Parcel
import android.os.Parcelable
import android.view.View
import com.acterics.racesclient.R
import com.acterics.racesclient.data.entity.Race
import com.acterics.racesclient.ui.schedule.ScheduleItemHolder
import com.acterics.racesclient.utils.Formats
import com.acterics.racesclient.utils.formattedDate
import com.acterics.racesclient.utils.setSupportTranslationName
import com.acterics.racesclient.utils.suffixedFormattedDate
import com.mikepenz.fastadapter.items.AbstractItem

/**
 * Created by root on 21.10.17.
 */
class ScheduleItem(val race: Race) : AbstractItem<ScheduleItem, ScheduleItemHolder>(), Parcelable {
    val holderTranslationName = "${race.title} holder"
    val titleTranslationName = "${race.title} title"
    val organizerTranslationName = "${race.title} organizer"

    override fun getViewHolder(v: View): ScheduleItemHolder {
        return ScheduleItemHolder(v)
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_race
    }

    override fun getType(): Int {
        return R.id.itemRace
    }

    override fun bindView(holder: ScheduleItemHolder, payloads: MutableList<Any>?) {
        super.bindView(holder, payloads)

        holder.tvRaceTitle.text = race.title
        holder.tvRaceOrganizer.text = race.organizer.name
        holder.tvRaceDate.text = race.date.suffixedFormattedDate(Formats.SCHEDULE_DATE_FORMAT)
        holder.tvRaceTime.text = race.date.formattedDate(Formats.SCHEDULE_TIME_FORMAT)

        holder.itemView.setSupportTranslationName(holderTranslationName)
        holder.tvRaceTitle.setSupportTranslationName(titleTranslationName)
        holder.tvRaceOrganizer.setSupportTranslationName(organizerTranslationName)

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