package com.acterics.racesclient.ui.schedule

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.*
import com.acterics.racesclient.R
import com.acterics.racesclient.ui.main.MainDrawerFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.mikepenz.fastadapter.adapters.FooterAdapter
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.fastadapter_extensions.items.ProgressItem
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener
import kotlinx.android.synthetic.main.fragment_schedule.*


/**
 * Created by root on 09.10.17.
 */
class ScheduleFragment: MainDrawerFragment(), ScheduleView {



    @InjectPresenter
    lateinit var presenter: SchedulePresenter

    private val scheduleAdapter = FastItemAdapter<ScheduleItem>()
    private val footerAdapter = FooterAdapter<ProgressItem>()

    private val endlessScrollListener = object: EndlessRecyclerOnScrollListener(footerAdapter) {
        override fun onLoadMore(currentPage: Int) {
            presenter.onLoadMore(currentPage)
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSchedule.layoutManager = LinearLayoutManager(context)
        rvSchedule.adapter = footerAdapter.wrap(scheduleAdapter)
        rvSchedule.itemAnimator = DefaultItemAnimator()
        rvSchedule.addOnScrollListener(endlessScrollListener)
    }

    override fun getToolbar(): Toolbar {
        return scheduleToolbar
    }

    override fun isLightTheme(): Boolean {
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_schedule, menu)
    }

    override fun resetScrollListener(page: Int) {
        endlessScrollListener.resetPageCount(page)
    }

    override fun showRaces(races: List<ScheduleItem>) {
        scheduleAdapter.add(scheduleAdapter.adapterItemCount, races)
    }

    override fun startScheduleLoading() {
        footerAdapter.clear()
        footerAdapter.add(ProgressItem().withEnabled(true))
    }

    override fun stopScheduleLoading() {
        footerAdapter.clear()
    }


}