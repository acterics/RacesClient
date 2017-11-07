package com.acterics.racesclient.presentation.racedetails

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.acterics.racesclient.R
import com.acterics.racesclient.presentation.racedetails.view.item.ParticipantItem
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem

/**
 * Created by root on 30.10.17.
 */
class ParticipantSubItem: AbstractExpandableItem<ParticipantItem, ParticipationExpandedHolder, ParticipantSubItem>() {

    var onConfirmBetListener: ((bet: Float, rating: Float, participationId: Long) -> Unit)? = null

    var awardChanged = false
    var betChanged = false

    override fun getLayoutRes(): Int = R.layout.item_participant_expanded
    override fun getViewHolder(v: View): ParticipationExpandedHolder = ParticipationExpandedHolder(v)
    override fun getType(): Int = R.id.itemParticipationExpanded


    override fun bindView(holder: ParticipationExpandedHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        clearAndSelect(holder)
        holder.etAward.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                awardChanged = false
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (!betChanged) {
//                    awardChanged = true
//                    val value = s?.toString()?.toIntOrNull() ?: 0
//                    val result = value / (1 + parent.participant.rating)
//                    holder.etBet.setText(result.toString())
//                }
                if (s.isNullOrEmpty()) {
                    holder.etAward.apply {
                        setText("0")
                        setSelection(0, 1)
                    }
                }


            }
        })

        holder.etBet.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                betChanged = false
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (!awardChanged) {
//                    betChanged = true
//                    val value = s?.toString()?.toIntOrNull() ?: 0
//                    val result = value * (1 + parent.participant.rating)
//                    holder.etAward.setText(result.toString())
//                }
                if (s.isNullOrEmpty()) {
                    holder.etBet.apply {
                        setText("0")
                        setSelection(0, 1)
                    }
                }
            }
        })
        holder.btClear.setOnClickListener { clearAndSelect(holder) }
        holder.btConfirm.setOnClickListener {
            if (holder.etBet.text.toString() != "0") {
                onConfirmBetListener?.invoke(holder.etBet.text.toString().toFloatOrNull() ?: 0.0f,
                        parent.participant.rating,
                        parent.participant.id)
            }
        }
    }


    private fun clearAndSelect(holder: ParticipationExpandedHolder) {
        holder.etBet.apply {
            requestFocus()
            setText("0")
            setSelection(0, text.length)
        }
        holder.etAward.apply {
            setText("0")
        }
    }
}