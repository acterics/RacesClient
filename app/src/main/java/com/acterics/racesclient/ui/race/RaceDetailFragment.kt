package com.acterics.racesclient.ui.race

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.transition.TransitionInflater
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.acterics.racesclient.R
import com.acterics.racesclient.common.ui.translation.ScheduleRaceTranslation
import com.acterics.racesclient.ui.schedule.PageProgressItem
import com.acterics.racesclient.common.extentions.getSupportDrawable
import com.acterics.racesclient.common.extentions.setSupportTranslationName
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.mikepenz.fastadapter.adapters.FooterAdapter
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.fragment_race.*

/**
 * Created by root on 15.10.17.
 */
class RaceDetailFragment: MvpAppCompatFragment(), RaceDetailView {

    companion object {
        const val EXTRA_TRANSLATION = "com.acterics.racesclient.ui.race.EXTRA_TRANSLATION"
    }
    lateinit var scheduleRaceTranslation: ScheduleRaceTranslation

    private val participantsAdapter = FastItemAdapter<ParticipantItem>()
    private val progressAdapter = FooterAdapter<PageProgressItem>()

    @InjectPresenter lateinit var presenter: RaceDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            scheduleRaceTranslation = it.getParcelable(EXTRA_TRANSLATION)
        }
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(EXTRA_TRANSLATION, scheduleRaceTranslation)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contextWrapper = ContextThemeWrapper(context, R.style.BlackAccentTheme)
        val localInflater = inflater.cloneInContext(contextWrapper)
        return localInflater.inflate(R.layout.fragment_race, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        holderRaceDetails.setSupportTranslationName(scheduleRaceTranslation.holderTranslationName)
        tvRaceTitle.setSupportTranslationName(scheduleRaceTranslation.titleTranslationName)
        tvRaceOrganizer.setSupportTranslationName(scheduleRaceTranslation.organizerTranslationName)

        tvRaceOrganizer.text = scheduleRaceTranslation.organizationTitle
        tvRaceTitle.text = scheduleRaceTranslation.raceTitle


        raceDetailsToolbar.navigationIcon = context.getSupportDrawable(R.drawable.ic_arrow_back_white)
        raceDetailsToolbar.title = getString(R.string.race)
        raceDetailsToolbar.setNavigationOnClickListener { presenter.onBack() }

        rvParticipants.layoutManager = LinearLayoutManager(context)
        rvParticipants.adapter = progressAdapter.wrap(participantsAdapter)
        rvParticipants.itemAnimator = DefaultItemAnimator()

    }

    override fun onViewAttached() {
        presenter.loadDetails(scheduleRaceTranslation.raceId)
    }

    override fun showParticipants(participants: List<ParticipantItem>) {
        participantsAdapter.add(participants)
    }

    override fun startParticipantsLoading() {
        progressAdapter.add(PageProgressItem())
    }

    override fun stopParticipantsLoading() {
        progressAdapter.clear()
    }

    override fun showError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}