package com.acterics.racesclient.presentation.schedule

import android.view.View
import com.acterics.racesclient.R
import com.acterics.racesclient.common.constants.Formats
import com.acterics.racesclient.common.extentions.formattedDate
import com.acterics.racesclient.common.extentions.setSupportTranslationName
import com.acterics.racesclient.common.extentions.suffixedFormattedDate
import com.acterics.racesclient.common.ui.translation.ScheduleRaceTranslation
import com.acterics.racesclient.domain.model.Race
import com.mikepenz.fastadapter.items.AbstractItem

/**
 * Created by root on 21.10.17.
 */
class ScheduleItem(var race: Race) : AbstractItem<ScheduleItem, ScheduleItemHolder>() {
    var scheduleRaceTranslation = ScheduleRaceTranslation(
            race.id, race.title, race.organizer!!.name,
            "${race.title} holder",
            "${race.title} title",
            "${race.title} organizer"
    )

    override fun getViewHolder(v: View): ScheduleItemHolder {
        return ScheduleItemHolder(v)
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_race
    }

    override fun getType(): Int {
        return R.id.itemRace
    }

    override fun bindView(holder: ScheduleItemHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)

        holder.tvRaceTitle.text = race.title
        holder.tvRaceOrganizer.text = race.organizer?.name
        holder.tvRaceDate.text = race.dateTime.suffixedFormattedDate(Formats.SCHEDULE_DATE)
        holder.tvRaceTime.text = race.dateTime.formattedDate(Formats.SCHEDULE_TIME)

        holder.itemView.setSupportTranslationName(scheduleRaceTranslation.holderTranslationName)
        holder.tvRaceTitle.setSupportTranslationName(scheduleRaceTranslation.titleTranslationName)
        holder.tvRaceOrganizer.setSupportTranslationName(scheduleRaceTranslation.organizerTranslationName)

    }
}