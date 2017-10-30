package com.acterics.racesclient.common.ui

import android.support.v7.widget.RecyclerView
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter

/**
 * Created by root on 29.10.17.
 */
typealias DefaultItem = IItem<*, *>
typealias DefaultItemAdapter = ItemAdapter<DefaultItem>
typealias DefaultFastItemAdapter = FastItemAdapter<DefaultItem>
