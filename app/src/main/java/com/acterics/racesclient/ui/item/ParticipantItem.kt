package com.acterics.racesclient.ui.item

import android.view.View
import com.acterics.racesclient.R
import com.acterics.racesclient.data.entity.Participant
import com.acterics.racesclient.ui.race.ParticipantHolder
import com.mikepenz.fastadapter.items.AbstractItem

/**
 * Created by root on 21.10.17.
 */
class ParticipantItem(private val participant: Participant): AbstractItem<ParticipantItem, ParticipantHolder>() {

    override fun getViewHolder(v: View): ParticipantHolder {
        return ParticipantHolder(v)
    }

    override fun getType(): Int {
        return R.id.itemParticipation
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_race_participants
    }

    override fun bindView(holder: ParticipantHolder, payloads: MutableList<Any>?) {
        super.bindView(holder, payloads)
        holder.tvHorseName.text = participant.horseId.toString()
        holder.tvHorseRating.text = participant.rating.toString()
    }

}