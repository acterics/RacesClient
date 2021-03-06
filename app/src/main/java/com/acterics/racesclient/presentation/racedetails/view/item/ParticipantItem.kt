package com.acterics.racesclient.presentation.racedetails.view.item

import android.view.View
import com.acterics.domain.model.Participant
import com.acterics.racesclient.R
import com.acterics.racesclient.common.ui.DefaultExpandableItem
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem

/**
 * Created by root on 21.10.17.
 */
class ParticipantItem(internal val participant: Participant):
        AbstractExpandableItem<ParticipantItem, ParticipantHolder, DefaultExpandableItem>() {

    var isBetOn: Boolean

    init {
        withSubItems(ArrayList<DefaultExpandableItem>().apply {
            participant.bets?.let {
                addAll(it.map { BetItem(it) })
            }
        })
        isBetOn = participant.bets?.isEmpty() != true
    }



    override fun getViewHolder(v: View) = ParticipantHolder(v)
    override fun getType() = R.id.itemParticipation
    override fun getLayoutRes() = R.layout.item_participant


    override fun bindView(holder: ParticipantHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        with (participant) {
            holder.tvHorseName.text = horse?.name
            holder.tvHorseRating.text = "$rating"
        }

        val betDrawableRes = if (isBetOn) R.drawable.ic_bet_on else R.drawable.ic_bet_off
        holder.btBet.setImageResource(betDrawableRes)
    }

    fun betOn() {
        isBetOn = true
    }


}