package com.acterics.racesclient.presentation.schedule.view

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.Toast
import com.acterics.racesclient.R
import com.acterics.racesclient.common.ui.MatchParentProgressItem
import com.acterics.racesclient.common.ui.SharedElementsHolder
import com.acterics.racesclient.common.ui.fragment.MainDrawerFragment
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.domain.interactor.GetRacesUseCase
import com.acterics.racesclient.presentation.schedule.ScheduleItem
import com.acterics.racesclient.presentation.schedule.presenter.SchedulePresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mikepenz.fastadapter.adapters.FooterAdapter
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.fastadapter_extensions.items.ProgressItem
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener
import kotlinx.android.synthetic.main.fragment_schedule.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject


/**
 * Created by root on 09.10.17.
 */
class ScheduleFragment: MainDrawerFragment(), ScheduleView, SharedElementsHolder {


    @Inject
    lateinit var router: Router

    @Inject
    lateinit var getRacesUseCase: GetRacesUseCase

    @InjectPresenter
    lateinit var presenter: SchedulePresenter

    @ProvidePresenter
    fun provideSchedulePresenter(): SchedulePresenter = SchedulePresenter(router, getRacesUseCase)

    private val scheduleAdapter = FastItemAdapter<ScheduleItem>()
    private val progressAdapter = FooterAdapter<ProgressItem>()


    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var endlessScrollListener : EndlessRecyclerOnScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contextWrapper = ContextThemeWrapper(context, R.style.BlackAccentTheme)
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
        rvSchedule.adapter = progressAdapter.wrap(scheduleAdapter)
        rvSchedule.itemAnimator = DefaultItemAnimator()

        endlessScrollListener = object: EndlessRecyclerOnScrollListener(layoutManager, 5, progressAdapter) {
            override fun onLoadMore(currentPage: Int) {
                rvSchedule.post { presenter.onLoadMore(currentPage) }
            }
        }

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

    override fun onDestroyView() {
        super.onDestroyView()
        scheduleAdapter.clear()
    }

    override fun startScheduleLoading(isFirstPage: Boolean) {
        progressAdapter.clear()
        val progressItem = if (isFirstPage) {
            MatchParentProgressItem()
        } else { ProgressItem() }
                .withEnabled(true)
        progressAdapter.add(progressItem)
    }

    override fun stopScheduleLoading() {
        progressAdapter.clear()
    }

    override fun getSharedElements(): Map<String, View?> {
        return presenter.sharedElements
    }

    override fun showError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun injectComponent() {
        ComponentsManager.mainComponent!!.inject(this)
    }
}