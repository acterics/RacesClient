package com.acterics.racesclient.presentation.profile.history.view

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acterics.racesclient.R
import com.acterics.racesclient.common.ui.DefaultFastItemAdapter
import com.acterics.racesclient.common.ui.DefaultItemAdapter
import com.acterics.racesclient.common.ui.PagingMvpViewDelegate
import com.acterics.racesclient.common.ui.fragment.BaseScopedFragment
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.domain.interactor.GetBetHistoryUseCase
import com.acterics.racesclient.domain.model.dto.HistoryBet
import com.acterics.racesclient.presentation.profile.history.HistoryBetItem
import com.acterics.racesclient.presentation.profile.history.presenter.ProfileHistoryPresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mikepenz.fastadapter.adapters.ItemAdapter.items
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener
import kotlinx.android.synthetic.main.fragment_profile_history.rvProfileHistory
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by root on 09.10.17.
 */
class ProfileHistoryFragment: BaseScopedFragment(), ProfileHistoryView {

    @Inject
    lateinit var pagingViewDelegate: PagingMvpViewDelegate

    @Inject
    lateinit var getHistoryUseCase: GetBetHistoryUseCase

    @Inject
    lateinit var router: Router

    @InjectPresenter
    lateinit var presenter: ProfileHistoryPresenter


    private val historyAdapter = DefaultFastItemAdapter()
    private lateinit var progressAdapter: DefaultItemAdapter

    private lateinit var endlessScrollListener : EndlessRecyclerOnScrollListener

    @ProvidePresenter
    fun providePresenter(): ProfileHistoryPresenter =
            ProfileHistoryPresenter(router, getHistoryUseCase)


    override fun injectComponent() {
        ComponentsManager.profileComponent!!.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_history, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressAdapter = items()
        historyAdapter.addAdapter(1, progressAdapter)

        val historyLayoutManager = LinearLayoutManager(context)
        endlessScrollListener = object:
                EndlessRecyclerOnScrollListener(historyLayoutManager, 5, progressAdapter) {
            override fun onLoadMore(currentPage: Int) {
                rvProfileHistory.post { presenter.onLoadMore(currentPage) }
            }
        }

        rvProfileHistory.apply {
            layoutManager = historyLayoutManager
            adapter = historyAdapter
            itemAnimator = DefaultItemAnimator()
            addOnScrollListener(endlessScrollListener)
        }
    }

    override fun showHistory(history: List<HistoryBetItem>) {
        historyAdapter.add(history)
    }

    override fun startPageLoading(isFirstPage: Boolean) {
        pagingViewDelegate.startPageLoading(progressAdapter, isFirstPage)
    }

    override fun stopPageLoading() {
        pagingViewDelegate.stopPageLoading(progressAdapter)
    }

    override fun showPagingError(message: String?, isFirstPage: Boolean) {
        pagingViewDelegate.showPageError(context, message, isFirstPage)
    }

    override fun resetPage(page: Int) {
        pagingViewDelegate.resetPage(endlessScrollListener, page)
    }
}