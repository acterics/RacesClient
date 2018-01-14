package com.acterics.racesclient.common.ui.other

import com.acterics.racesclient.R
import com.mikepenz.fastadapter_extensions.items.ProgressItem

/**
 * Created by root on 16.10.17.
 */
class MatchParentProgressItem : ProgressItem() {

    override fun getType() = R.id.holder_progress
    override fun getLayoutRes() = R.layout.item_race_first_progress
}