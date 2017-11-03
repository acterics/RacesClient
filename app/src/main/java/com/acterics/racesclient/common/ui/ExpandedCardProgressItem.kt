package com.acterics.racesclient.common.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.acterics.racesclient.R
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem

/**
 * Created by root on 01.11.17.
 */
class ExpandedCardProgressItem : AbstractExpandableItem<DefaultExpandableItem, ExpandedCardProgressItem.ViewHolder, DefaultExpandableItem>() {


    override fun getType(): Int = R.id.itemCardProgress
    override fun getLayoutRes(): Int = R.layout.item_card_progress
    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        protected var progressBar: ProgressBar = view.findViewById<View>(R.id.progress_bar) as ProgressBar
    }
}