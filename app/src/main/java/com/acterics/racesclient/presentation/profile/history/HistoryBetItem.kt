package com.acterics.racesclient.presentation.profile.history

import android.support.v7.widget.RecyclerView
import android.view.View
import com.acterics.racesclient.domain.model.dto.HistoryBet
import com.mikepenz.fastadapter.items.AbstractItem

/**
 * Created by root on 03.11.17.
 */
class HistoryBetItem(historyBet: HistoryBet):
        AbstractItem<HistoryBetItem, HistoryBetItemHolder>() {


    override fun getViewHolder(v: View): HistoryBetItemHolder = HistoryBetItemHolder(v)

    override fun getType(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutRes(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}