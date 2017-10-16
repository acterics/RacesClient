package com.acterics.racesclient.ui.schedule

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.*
import com.acterics.racesclient.R
import com.acterics.racesclient.ui.base.SharedElementsHolder
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
class ScheduleFragment: MainDrawerFragment(), ScheduleView, SharedElementsHolder {


    @InjectPresenter
    lateinit var presenter: SchedulePresenter

    private val scheduleAdapter = FastItemAdapter<ScheduleItem>()
    private val footerAdapter = FooterAdapter<ProgressItem>()


    private lateinit var layoutManager: RecyclerView.LayoutManager
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
        val contextWrapper = ContextThemeWrapper(context, R.style.ScheduleTheme)
        val localInflater = inflater.cloneInContext(contextWrapper)
        return localInflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(scheduleAdapter.saveInstanceState(outState))
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(context)

        scheduleAdapter.withOnClickListener { v, _, item, _ -> presenter.onScheduleItemClick(v, item) }
        rvSchedule.layoutManager = layoutManager
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
    override fun resetPage(page: Int) {
        endlessScrollListener.resetPageCount(page)
    }

    override fun showRaces(races: List<ScheduleItem>) {
        scheduleAdapter.add(scheduleAdapter.adapterItemCount, races)
    }

    override fun startScheduleLoading(isFirstPage: Boolean) {
        footerAdapter.clear()
        val progressItem = if (isFirstPage) { PageProgressItem() } else { ProgressItem() }
                .withEnabled(true)
        footerAdapter.add(progressItem)
    }

    override fun stopScheduleLoading() {
        footerAdapter.clear()
    }

    override fun getSharedElements(): Map<String, View?> {
        return presenter.sharedElements
    }

}