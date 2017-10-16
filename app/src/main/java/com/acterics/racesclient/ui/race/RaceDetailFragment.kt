package com.acterics.racesclient.ui.race

import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.Toolbar
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acterics.racesclient.R
import com.acterics.racesclient.data.entity.Race
import com.acterics.racesclient.ui.main.MainDrawerFragment
import com.acterics.racesclient.ui.schedule.ScheduleItem
import com.acterics.racesclient.utils.getSupportDrawable
import com.acterics.racesclient.utils.setSupportTranslationName
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_race.*
import timber.log.Timber

/**
 * Created by root on 15.10.17.
 */
class RaceDetailFragment: MvpAppCompatFragment(), RaceDetailView {

    companion object {
        const val EXTRA_RACE = "com.acterics.racesclient.ui.race.EXTRA_RACE"
    }

    lateinit var scheduleItem: ScheduleItem

    @InjectPresenter
    lateinit var presenter: RaceDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            scheduleItem = savedInstanceState.getParcelable(EXTRA_RACE)
        }
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(EXTRA_RACE, scheduleItem)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_race, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        holderRaceDetails.setSupportTranslationName(scheduleItem.holderTranslationName)
        tvRaceTitle.setSupportTranslationName(scheduleItem.titleTranslationName)
        tvRaceOrganizer.setSupportTranslationName(scheduleItem.organizerTranslationName)

        tvRaceOrganizer.text = scheduleItem.race.organizer.name
        tvRaceTitle.text = scheduleItem.race.title


        raceDetailsToolbar.navigationIcon = context.getSupportDrawable(R.drawable.ic_arrow_back_white)
        raceDetailsToolbar.title = getString(R.string.race)
        raceDetailsToolbar.setNavigationOnClickListener { presenter.onBack() }
    }




}