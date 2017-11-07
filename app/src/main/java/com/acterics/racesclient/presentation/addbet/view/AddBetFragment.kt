package com.acterics.racesclient.presentation.addbet.view

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acterics.racesclient.R
import com.acterics.racesclient.common.constants.Extra
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            addBetTranslation = it.getParcelable(Extra.TRANSLATION)
        }
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(Extra.TRANSLATION, addBetTranslation)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_bet, container, false)
    }

    override fun injectComponent() {
        ComponentsManager.mainComponent!!.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        holderAddBet.setSupportTranslationName(addBetTranslation.addBetHolder)
    }

}