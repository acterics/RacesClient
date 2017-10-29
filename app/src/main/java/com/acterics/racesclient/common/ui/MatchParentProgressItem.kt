package com.acterics.racesclient.common.ui

import com.acterics.racesclient.R
import com.mikepenz.fastadapter_extensions.items.ProgressItem

/**
 * Created by root on 16.10.17.
 */
class MatchParentProgressItem : ProgressItem() {

    override fun getType(): Int {
        return R.id.holder_progress
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_race_first_progress
    }
}