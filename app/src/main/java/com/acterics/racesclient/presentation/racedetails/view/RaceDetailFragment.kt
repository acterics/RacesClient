package com.acterics.racesclient.presentation.racedetails.view

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.transition.TransitionInflater
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.acterics.racesclient.R
import com.acterics.racesclient.common.constants.Extra
import com.acterics.racesclient.common.extentions.getNavigationAvd
import com.acterics.racesclient.common.extentions.getSupportDrawable
import com.acterics.racesclient.common.extentions.setSupportTranslationName
import com.acterics.racesclient.common.ui.*
import com.acterics.racesclient.common.ui.fragment.BaseScopedFragment
import com.acterics.racesclient.common.ui.other.MatchParentProgressItem
import com.acterics.racesclient.common.ui.translation.ScheduleRaceTranslation
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.domain.interactor.GetRaceDetailsUseCase
import com.acterics.racesclient.presentation.racedetails.view.item.ParticipantItem
import com.acterics.racesclient.presentation.racedetails.presenter.RaceDetailPresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mikepenz.fastadapter.adapters.ItemAdapter.items
import com.mikepenz.fastadapter.expandable.ExpandableExtension
import kotlinx.android.synthetic.main.fragment_race.*
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by root on 15.10.17.
 */
class RaceDetailFragment: BaseScopedFragment(), RaceDetailView, SharedElementsHolder {


    lateinit var scheduleRaceTranslation: ScheduleRaceTranslation



    private val participantsAdapter = DefaultFastItemAdapter()
    private val expandableExtension = ExpandableExtension<DefaultItem>()
    private lateinit var progressAdapter: DefaultItemAdapter

    @Inject
    lateinit var getRaceDetailsUseCase: GetRaceDetailsUseCase

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var toolbar: Toolbar

    @InjectPresenter lateinit var presenter: RaceDetailPresenter

    private val navigationAvd by lazy {
            ResourcesCompat.getDrawable(resources, R.drawable.avd_back_to_close, null) as AnimatedVectorDrawable
    }

    @ProvidePresenter
    fun provideRaceDetailsPresenter(): RaceDetailPresenter =
            RaceDetailPresenter(router, getRaceDetailsUseCase)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            scheduleRaceTranslation = it.getParcelable(Extra.TRANSLATION)
        }
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(Extra.TRANSLATION, scheduleRaceTranslation)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contextWrapper = ContextThemeWrapper(context, R.style.BlackAccentTheme)
        val localInflater = inflater.cloneInContext(contextWrapper)
        return localInflater.inflate(R.layout.fragment_race, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("onViewCreated:RACE DETAIL ")
        holderRaceDetails.setSupportTranslationName(scheduleRaceTranslation.holderTranslationName)
        tvRaceTitle.setSupportTranslationName(scheduleRaceTranslation.titleTranslationName)
        tvRaceOrganizer.setSupportTranslationName(scheduleRaceTranslation.organizerTranslationName)

        tvRaceOrganizer.text = scheduleRaceTranslation.organizationTitle
        tvRaceTitle.text = scheduleRaceTranslation.raceTitle

        navigationAvd.reset()
        toolbar.apply {
            title = getString(R.string.race)
            navigationIcon = navigationAvd
            setNavigationOnClickListener { presenter.onBack() }
        }

        progressAdapter = items()

        participantsAdapter.apply {
            addAdapter(1, progressAdapter)
            addExtension(expandableExtension)
        }

        rvParticipants.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = participantsAdapter
            itemAnimator = DefaultItemAnimator()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.loaded = false
    }

    override fun onPresenterAttached() {
        presenter.loadDetails(scheduleRaceTranslation.raceId)
    }


    override fun showParticipants(participants: List<ParticipantItem>) {
        Timber.e("showParticipants: ")

        participantsAdapter.add(participants)
    }

    override fun startParticipantsLoading() {
        progressAdapter.add(MatchParentProgressItem())
    }

    override fun stopParticipantsLoading() {
        progressAdapter.clear()
    }

    override fun showError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun injectComponent() {
        ComponentsManager.mainComponent!!.inject(this)
    }

    override fun notifyNewBet(identifier: Long, previousSize:Int) {
        expandableExtension.notifyAdapterSubItemsChanged(participantsAdapter.getPosition(identifier), previousSize)
        participantsAdapter.notifyAdapterDataSetChanged()
    }

    override fun getSharedElements(): Map<String, View?> {
        return presenter.sharedElements
    }

    override fun startNavigationAnimation() {
        toolbar.getNavigationAvd()?.start()
    }
}

