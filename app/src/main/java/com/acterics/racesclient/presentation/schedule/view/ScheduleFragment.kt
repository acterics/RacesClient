package com.acterics.racesclient.presentation.schedule.view

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.*
import com.acterics.racesclient.R
import com.acterics.racesclient.common.dsl.addEndlessOnScrollListener
import com.acterics.racesclient.common.ui.*
import com.acterics.racesclient.common.ui.fragment.BaseScopedFragment
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.domain.interactor.GetRacesUseCase
import com.acterics.racesclient.presentation.schedule.ScheduleItem
import com.acterics.racesclient.presentation.schedule.presenter.SchedulePresenter
import com.acterics.racesclient.presentation.navigation.*
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mikepenz.fastadapter.adapters.ItemAdapter.items
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener
import kotlinx.android.synthetic.main.fragment_schedule.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject


/**
 * Created by root on 09.10.17.
 */
class ScheduleFragment: BaseScopedFragment(),
        ScheduleView,
        BaseToolbarAnimationView,
        SharedElementsHolder {

    @Inject lateinit var toolbar: Toolbar
    @Inject lateinit var router: Router
    @Inject lateinit var getRacesUseCase: GetRacesUseCase
    @Inject lateinit var pagingViewDelegate: PagingMvpViewDelegate


    @InjectPresenter
    lateinit var presenter: SchedulePresenter

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var toolbarAnimationPresenter: BaseToolbarAnimationPresenter


    @ProvidePresenter
    fun provideSchedulePresenter(): SchedulePresenter = SchedulePresenter(router, getRacesUseCase)

    private val scheduleAdapter = DefaultFastItemAdapter()
    private lateinit var progressAdapter: DefaultItemAdapter


    private val navigationAvd by lazy {
        ResourcesCompat.getDrawable(resources, R.drawable.avd_menu_to_back_white, null) as AnimatedVectorDrawable
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contextWrapper = ContextThemeWrapper(context, R.style.BlackAccentTheme)
        val localInflater = inflater.cloneInContext(contextWrapper)
        return localInflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(scheduleAdapter.saveInstanceState(outState))
    }

    //FIXME duplicate loading items
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressAdapter = items()
        toolbar.title = getString(R.string.schedule)

        scheduleAdapter.apply {
            addAdapter(1, progressAdapter)
            withOnClickListener { v, _, item, _ ->
                presenter.onScheduleItemClick(v, item as ScheduleItem)
            }
        }

        val historyLayoutManager = LinearLayoutManager(context)

        rvSchedule.apply {
            layoutManager = historyLayoutManager
            adapter = scheduleAdapter
            itemAnimator = DefaultItemAnimator()
            addEndlessOnScrollListener {
                footerAdapter = progressAdapter
                pagingDelegate = pagingViewDelegate
                visibleThreshold = 5
                onLoadMore {
                    page -> rvSchedule.post { presenter.onLoadMore(page) }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_schedule, menu)
    }


    override fun showRaces(races: List<ScheduleItem>) {
        scheduleAdapter.add(scheduleAdapter.adapterItemCount, races)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scheduleAdapter.clear()
        toolbar.removeCallbacks(null)
    }

    override fun resetPage(page: Int) {
        pagingViewDelegate.resetPage(page)
    }

    override fun startPageLoading(isFirstPage: Boolean) {
        pagingViewDelegate.startPageLoading(isFirstPage)
    }

    override fun stopPageLoading() {
        pagingViewDelegate.stopPageLoading()
    }

    override fun showPagingError(message: String?, isFirstPage: Boolean) {
        pagingViewDelegate.showPageError(context, message, isFirstPage)
    }

    override fun getSharedElements(): Map<String, View?> =
        presenter.sharedElements


    override fun injectComponent() {
        ComponentsManager.mainComponent!!.inject(this)
    }

    //TODO Add compatibility
    override fun bindNavigationIcon() {
        navigationAvd.reset()
        toolbar.navigationIcon = navigationAvd
    }

    override fun postBindNavigationIcon() {
        toolbar.postDelayed({ bindNavigationIcon() }, 500)
    }

    override fun startToolbarAnimation() {
        navigationAvd.start()
    }
}