package com.acterics.racesclient.presentation.addbet.presenter

import android.widget.EditText
import com.acterics.domain.interactor.RaceInteractor
import com.acterics.domain.model.Bet
import com.acterics.domain.model.Participant
import com.acterics.racesclient.presentation.addbet.view.AddBetView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

/**
 * Created by root on 09.11.17.
 */
@InjectViewState
class AddBetPresenter(private val router: Router,
                      private val raceInteractor: RaceInteractor):
        MvpPresenter<AddBetView>() {


    fun onBack() {
        router.exit()
    }


    fun onChangeValue(etValue: EditText, operator: (Float) -> Float) {
        etValue.apply {
                    requestFocus()
                    setSelection(0, text.length)
                }
                .run { text.toString() }
                .toFloat()
                .let { operator(it) }
                .let { if (it < 0) 0.0f else it }
                .toString()
                .let { etValue.setText(it) }
    }

    fun onAddBet(bet: CharSequence, rating: Float, id: Long) {
        bet.toString().takeIf { bet.isNotEmpty() }
                ?.let { it.toFloat().takeIf { it > 0.0f } }
                ?.let { raceInteractor.addBet(Bet(it, rating), Participant(id))
                        .subscribeBy(
                                onComplete = {
                                    viewState.successAdd()
                                    router.exit()
                                },
                                onError = {
                                    viewState.errorAdd(it)
                                }
                        )
                }
    }

}