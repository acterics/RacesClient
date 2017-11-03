package com.acterics.racesclient.presentation.racedetails

import android.view.View
import com.acterics.racesclient.R
import com.acterics.racesclient.domain.model.Bet
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem

/**
 * Created by root on 31.10.17.
 */
class BetItem(private val bet: Bet):
        AbstractExpandableItem<ParticipantItem, BetHolder, BetItem>() {
    override fun getViewHolder(v: View): BetHolder = BetHolder(v)
    override fun getType(): Int = R.id.itemBet
    override fun getLayoutRes(): Int = R.layout.item_bet

    override fun bindView(holder: BetHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.tvBet.text = bet.bet.toString()
        holder.tvAward.text = (bet.bet * (1 + bet.rating)).toString()
    }

}