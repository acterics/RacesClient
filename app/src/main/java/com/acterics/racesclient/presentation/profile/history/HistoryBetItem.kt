package com.acterics.racesclient.presentation.profile.history

import android.view.View
import com.acterics.racesclient.R
import com.acterics.racesclient.common.constants.Formats
import com.acterics.racesclient.common.extentions.formattedDate
import com.acterics.racesclient.domain.model.dto.HistoryBet
import com.mikepenz.fastadapter.items.AbstractItem

/**
 * Created by root on 03.11.17.
 */
class HistoryBetItem(val historyBet: HistoryBet):
        AbstractItem<HistoryBetItem, HistoryBetItemHolder>() {


    override fun getViewHolder(v: View): HistoryBetItemHolder = HistoryBetItemHolder(v)
    override fun getType(): Int = R.id.itemProfileHistory
    override fun getLayoutRes(): Int = R.layout.item_profile_history

    override fun bindView(holder: HistoryBetItemHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)

        holder.tvDate.text = historyBet.date.formattedDate(Formats.PROFILE_HISTORY_DATE)
        holder.tvBet.text = historyBet.bet.bet.toString()
        holder.tvHorseName.text = historyBet.horseName

        val resultRes = when (historyBet.success) {
            true -> R.drawable.ic_arrow_up_green
            false -> R.drawable.ic_arrow_down_red
            else -> R.drawable.ic_access_time
        }
        holder.tvResult.text =  historyBet.result?.toString()
        holder.imResult.setImageResource(resultRes)
    }
}