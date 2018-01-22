package com.acterics.racesclient.presentation.profile.history.view

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acterics.domain.interactor.ProfileInteractor
import com.acterics.racesclient.R
import com.acterics.racesclient.common.dsl.addEndlessOnScrollListener
import com.acterics.racesclient.common.ui.DefaultFastItemAdapter
import com.acterics.racesclient.common.ui.DefaultItemAdapter
import com.acterics.racesclient.common.ui.PagingMvpViewDelegate
import com.acterics.racesclient.common.ui.fragment.BaseScopedFragment
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.presentation.profile.history.HistoryBetHeaderItem
import com.acterics.racesclient.presentation.profile.history.HistoryBetItem
import com.acterics.racesclient.presentation.profile.history.presenter.ProfileHistoryPresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.fragment_profile_history.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by root on 09.10.17.
 */
class ProfileHistoryFragment: BaseScopedFragment(), ProfileHistoryView {

    @Inject lateinit var pagingViewDelegate: PagingMvpViewDelegate
    @Inject lateinit var router: Router
    @Inject lateinit var profileInteractor: ProfileInteractor
    @InjectPresenter lateinit var presenter: ProfileHistoryPresenter

    private val historyAdapter = DefaultFastItemAdapter()
    private lateinit var progressAdapter: DefaultItemAdapter
    private lateinit var headerAdapter: DefaultItemAdapter


    @ProvidePresenter
    fun providePresenter(): ProfileHistoryPresenter =
            ProfileHistoryPresenter(router, profileInteractor)


    override fun injectComponent() {
        ComponentsManager.profileComponent!!.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_profile_history, container, false)

    override fun onDestroyView() {
        super.onDestroyView()
        historyAdapter.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressAdapter = ItemAdapter()
        headerAdapter = ItemAdapter()

        with(historyAdapter) {
            addAdapter(0, headerAdapter)
            addAdapter(2, progressAdapter)
        }

        headerAdapter.add(HistoryBetHeaderItem().apply {
            dateClickListener = { presenter.onSortByDate() }
            nameClickListener = { presenter.onSortByName() }
            betClickListener = { presenter.onSortByBet() }
            resultClickListener = { presenter.onSortByResult() }
        })


        val historyLayoutManager = LinearLayoutManager(context)

        with(rvProfileHistory) {
            layoutManager = historyLayoutManager
            adapter = historyAdapter
            itemAnimator = DefaultItemAnimator()
            addEndlessOnScrollListener {
                footerAdapter = progressAdapter
                pagingDelegate = pagingViewDelegate
                visibleThreshold = 5
                onLoadMore {
                    page -> rvProfileHistory.post { presenter.onLoadMore(page) }
                }
            }
        }

    }

    override fun showHistory(history: List<HistoryBetItem>) {
        historyAdapter.add(history)
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

    override fun resetPage(page: Int) {
        pagingViewDelegate.resetPage(page)
    }

    override fun sortBy(comparator: Comparator<IItem<*, *>>) {
        historyAdapter.apply {
            adapterItems.sortWith(comparator)
            notifyAdapterDataSetChanged()
        }
    }
}