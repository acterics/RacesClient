package com.acterics.racesclient.presentation.racedetails

import android.view.View
import com.acterics.racesclient.R
import com.acterics.racesclient.common.ui.DefaultExpandableItem
import com.acterics.racesclient.data.database.entity.ParticipantEntity
import com.acterics.racesclient.domain.model.Participant
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem

/**
 * Created by root on 21.10.17.
 */
class ParticipantItem(internal val participant: Participant):
        AbstractExpandableItem<ParticipantItem, ParticipantHolder, DefaultExpandableItem>() {

    init {
        withSubItems(ArrayList<DefaultExpandableItem>().apply {
            addAll(participant.bets.map { BetItem(it) })
        })
    }

    var isBetOn = false

    override fun getViewHolder(v: View): ParticipantHolder {
        return ParticipantHolder(v)
    }

    override fun getType(): Int {
        return R.id.itemParticipation
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_participant
    }

    override fun bindView(holder: ParticipantHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.tvHorseName.text = participant.horse?.name
        holder.tvHorseRating.text = participant.rating.toString()
        val betDrawableRes = if (isBetOn) R.drawable.ic_bet_on else R.drawable.ic_bet_off


        holder.btBet.setImageResource(betDrawableRes)
    }

    fun betOn() {
        isBetOn = true
    }


}