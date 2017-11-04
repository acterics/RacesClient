package com.acterics.racesclient.presentation.profile.history

import android.view.View
import com.acterics.racesclient.R
import com.mikepenz.fastadapter.items.AbstractItem

/**
 * Created by root on 03.11.17.
 */
class HistoryBetHeaderItem: AbstractItem<HistoryBetHeaderItem, HistoryBetItemHolder>() {

    var dateClickListener: (() -> Unit)? = null
    var betClickListener: (() -> Unit)? = null
    var nameClickListener: (() -> Unit)? = null
    var resultClickListener: (() -> Unit)? = null

    override fun getLayoutRes(): Int = R.layout.item_profile_history_header
    override fun getViewHolder(v: View): HistoryBetItemHolder = HistoryBetItemHolder(v)
    override fun getType(): Int = R.id.itemProfileHistoryHeader



    override fun bindView(holder: HistoryBetItemHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.tvDate.apply {
            setText(R.string.history_race_date)
            setOnClickListener { dateClickListener?.invoke() }
        }
        holder.tvBet.apply {
            setText(R.string.history_bet)
            setOnClickListener { betClickListener?.invoke() }
        }
        holder.tvHorseName.apply {
            setText(R.string.history_horse_name)
            setOnClickListener { nameClickListener?.invoke() }
        }

        holder.tvResult.apply {
            setText(R.string.history_result)
            setOnClickListener { resultClickListener?.invoke() }
        }

    }
}