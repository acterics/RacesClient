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
import com.acterics.domain.interactor.RaceInteractor
import com.acterics.racesclient.R
import com.acterics.racesclient.common.constants.Extra
import com.acterics.racesclient.common.extentions.setSupportTranslationName
import com.acterics.racesclient.common.ui.DefaultFastItemAdapter
import com.acterics.racesclient.common.ui.DefaultItem
import com.acterics.racesclient.common.ui.DefaultItemAdapter
import com.acterics.racesclient.common.ui.SharedElementsHolder
import com.acterics.racesclient.common.ui.fragment.BaseScopedFragment
import com.acterics.racesclient.common.ui.other.MatchParentProgressItem
import com.acterics.racesclient.common.ui.translation.ScheduleRaceTranslation
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.presentation.navigation.ToolbarAnimationPresenter
import com.acterics.racesclient.presentation.navigation.ToolbarAnimationView
import com.acterics.racesclient.presentation.racedetails.presenter.RaceDetailPresenter
import com.acterics.racesclient.presentation.racedetails.view.item.ParticipantItem
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mikepenz.fastadapter.adapters.ItemAdapter.items
import com.mikepenz.fastadapter.expandable.ExpandableExtension
import kotlinx.android.synthetic.main.fragment_race.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by root on 15.10.17.
 */
class RaceDetailFragment: BaseScopedFragment(),
        RaceDetailView,
        ToolbarAnimationView,
        SharedElementsHolder {

    lateinit var scheduleRaceTranslation: ScheduleRaceTranslation

    private val participantsAdapter = DefaultFastItemAdapter()
    private val expandableExtension = ExpandableExtension<DefaultItem>()

    private lateinit var progressAdapter: DefaultItemAdapter

    @Inject lateinit var raceInteractor: RaceInteractor
    @Inject lateinit var router: Router
    @Inject lateinit var toolbar: Toolbar


    @InjectPresenter
    lateinit var presenter: RaceDetailPresenter

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var toolbarAnimationPresenter: ToolbarAnimationPresenter

    private val navigationAvd by lazy {
        ResourcesCompat.getDrawable(resources, R.drawable.avd_back_to_close_white, null) as AnimatedVectorDrawable
    }
    private val backNavigationAvd by lazy {
        ResourcesCompat.getDrawable(resources, R.drawable.avd_back_to_menu_white, null) as AnimatedVectorDrawable
    }

    @ProvidePresenter
    fun provideRaceDetailsPresenter(): RaceDetailPresenter =
            RaceDetailPresenter(router, raceInteractor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let { scheduleRaceTranslation = it.getParcelable(Extra.TRANSLATION) }
        sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(android.R.transition.move)
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
        with(scheduleRaceTranslation) {
            holderRaceDetails.setSupportTranslationName(holderTranslationName)
            tvRaceTitle.setSupportTranslationName(titleTranslationName)
            tvRaceTitle.text = raceTitle

            tvRaceOrganizer.setSupportTranslationName(organizerTranslationName)
            tvRaceOrganizer.text = organizationTitle
        }

        with(toolbar) {
            title = getString(R.string.race)
            setNavigationOnClickListener { presenter.onBack() }
        }

        progressAdapter = items()

        with(participantsAdapter) {
            addAdapter(1, progressAdapter)
            addExtension(expandableExtension)
        }

        with(rvParticipants) {
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

    override fun getSharedElements(): Map<String, View?> = presenter.sharedElements


    //TODO Add compatibility
    override fun bindNavigationIcon() {
        backNavigationAvd.reset()
        toolbar.navigationIcon = backNavigationAvd
    }

    override fun postBindNavigationIcon() {
        toolbar.postDelayed({ bindNavigationIcon() }, 500)
    }

    override fun startBackToolbarAnimation() {
        backNavigationAvd.reset()
        backNavigationAvd.start()
    }

    override fun startToolbarNavigation() {
        navigationAvd.reset()
        toolbar.navigationIcon = navigationAvd
        navigationAvd.start()
    }
}

