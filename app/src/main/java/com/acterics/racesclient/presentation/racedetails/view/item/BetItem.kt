package com.acterics.racesclient.presentation.racedetails.view.item

import android.view.View
import com.acterics.racesclient.R
import com.acterics.domain.model.Bet
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
        with (bet) {
            holder.tvBet.text = "$bet"
            holder.tvAward.text = (bet * (1 + rating)).toString()
        }

    }

}