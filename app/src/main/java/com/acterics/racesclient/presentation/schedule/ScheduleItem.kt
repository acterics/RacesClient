package com.acterics.racesclient.presentation.schedule

import android.view.View
import com.acterics.domain.model.Race
import com.acterics.racesclient.R
import com.acterics.racesclient.common.constants.Formats
import com.acterics.racesclient.common.extentions.formattedDate
import com.acterics.racesclient.common.extentions.setSupportTranslationName
import com.acterics.racesclient.common.extentions.suffixedFormattedDate
import com.acterics.racesclient.common.ui.translation.ScheduleRaceTranslation
import com.mikepenz.fastadapter.items.AbstractItem

/**
 * Created by root on 21.10.17.
 */
class ScheduleItem(var race: Race) : AbstractItem<ScheduleItem, ScheduleItemHolder>() {
    var scheduleRaceTranslation = ScheduleRaceTranslation(
            race.id,
            race.title,
            race.organizer!!.name,
            "${race.title} holder",
            "${race.title} title",
            "${race.title} organizer"
    )

    override fun getViewHolder(v: View) = ScheduleItemHolder(v)
    override fun getLayoutRes() = R.layout.item_race
    override fun getType() = R.id.itemRace

    override fun bindView(holder: ScheduleItemHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)

        with(race) {
            holder.tvRaceTitle.text = title
            holder.tvRaceOrganizer.text = organizer?.name
            holder.tvRaceDate.text = dateTime.suffixedFormattedDate(Formats.SCHEDULE_DATE)
            holder.tvRaceTime.text = dateTime.formattedDate(Formats.SCHEDULE_TIME)
        }

        with(scheduleRaceTranslation) {
            holder.itemView.setSupportTranslationName(holderTranslationName)
            holder.tvRaceTitle.setSupportTranslationName(titleTranslationName)
            holder.tvRaceOrganizer.setSupportTranslationName(organizerTranslationName)
        }

    }
}