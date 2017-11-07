package com.acterics.racesclient.presentation.addbet.view

import android.os.Bundle
import android.view.View
import com.acterics.racesclient.common.extentions.setSupportTranslationName
import com.acterics.racesclient.common.ui.fragment.BaseScopedFragment
import com.acterics.racesclient.common.ui.translation.AddBetTranslation
import com.acterics.racesclient.di.ComponentsManager
import kotlinx.android.synthetic.main.fragment_add_bet.*

/**
 * Created by root on 07.11.17.
 */
class AddBetFragment: BaseScopedFragment() {

    lateinit var addBetTranslation: AddBetTranslation

    override fun injectComponent() {
        ComponentsManager.mainComponent!!.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        holderAddBet.setSupportTranslationName(addBetTranslation.addBetHolder)
    }

}