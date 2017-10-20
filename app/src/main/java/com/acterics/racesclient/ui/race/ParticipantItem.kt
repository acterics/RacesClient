package com.acterics.racesclient.ui.race

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.acterics.racesclient.R
import com.acterics.racesclient.data.entity.Participant
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.item_race_participants.view.*

/**
 * Created by root on 19.10.17.
 */
class ParticipantItem(private val participant: Participant): AbstractItem<ParticipantItem, ParticipantItem.ParticipantViewHolder>() {

    private var isBet = false

    override fun getViewHolder(v: View): ParticipantViewHolder {
        return ParticipantViewHolder(v)
    }

    override fun getType(): Int {
        return R.id.itemParticipation
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_race_participants
    }

    override fun bindView(holder: ParticipantViewHolder, payloads: MutableList<Any>?) {
        super.bindView(holder, payloads)
        holder.tvHorseName.text = participant.horse.name
        holder.tvHorseRating.text = participant.rating.toString()
    }


    class ParticipantViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvHorseName: TextView by lazy { itemView.findViewById<TextView>(R.id.tvHorseName) }
        val tvHorseRating: TextView by lazy { itemView.findViewById<TextView>(R.id.tvHorseRating) }
        val btBet: ImageButton by lazy { itemView.findViewById<ImageButton>(R.id.btBet) }
        val vHorseIndicator: View by lazy { itemView.findViewById<View>(R.id.vHorseIndicator) }
    }
}