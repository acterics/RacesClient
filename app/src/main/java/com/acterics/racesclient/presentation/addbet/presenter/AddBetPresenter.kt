package com.acterics.racesclient.presentation.addbet.presenter

import android.widget.EditText
import com.acterics.racesclient.domain.interactor.AddBetUseCase
import com.acterics.racesclient.presentation.addbet.view.AddBetFragment
import com.acterics.racesclient.presentation.addbet.view.AddBetView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import kotlinx.android.synthetic.main.fragment_add_bet.*
import ru.terrakok.cicerone.Router

/**
 * Created by root on 09.11.17.
 */
@InjectViewState
class AddBetPresenter(private val router: Router,
                      private val addBetUseCase: AddBetUseCase):
        MvpPresenter<AddBetView>() {


    fun onBack() {
        router.exit()
    }


    fun onChangeValue(etValue: EditText, operator: (Float) -> Float) {
        etValue.apply { requestFocus() }
                .apply { setSelection(0, text.length) }
                .run { text.toString() }
                .toFloat()
                .let { operator(it) }
                .let { if (it < 0) 0.0f else it }
                .toString()
                .let { etValue.setText(it) }
    }

}