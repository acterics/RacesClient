package com.acterics.racesclient.presentation.racedetails.view.item

import android.view.View
import com.acterics.racesclient.R
import com.acterics.racesclient.common.ui.translation.AddBetTranslation
import com.acterics.racesclient.presentation.racedetails.ParticipantSubItem
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem

/**
 * Created by root on 07.11.17.
 */
class AddBetItem: AbstractExpandableItem<ParticipantItem, AddBetHolder, ParticipantSubItem>() {

    private val addBetTranslation: AddBetTranslation by lazy {
        AddBetTranslation("${parent.participant.id} addBetHolder",
                parent.participant.id)
    }
    var addBetClickListener: ((addBetTranslation: AddBetTranslation, view: View?) -> Unit)? = null


    override fun getViewHolder(v: View): AddBetHolder = AddBetHolder(v)
    override fun getLayoutRes(): Int = R.layout.item_add_bet
    override fun getType(): Int = R.id.itemAddBet

    override fun bindView(holder: AddBetHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.btAddBet.setOnClickListener { view ->
            addBetClickListener?.invoke(addBetTranslation, view)
        }
    }




}