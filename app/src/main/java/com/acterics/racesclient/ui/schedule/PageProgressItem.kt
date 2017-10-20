package com.acterics.racesclient.ui.schedule

import com.acterics.racesclient.R
import com.mikepenz.fastadapter_extensions.items.ProgressItem

/**
 * Created by root on 16.10.17.
 */
class PageProgressItem: ProgressItem() {

    override fun getType(): Int {
        return R.id.holder_progress
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_race_first_progress
    }
}