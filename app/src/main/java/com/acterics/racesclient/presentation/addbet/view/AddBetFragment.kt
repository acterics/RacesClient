package com.acterics.racesclient.presentation.addbet.view

import android.os.Bundle
import android.support.v7.widget.Toolbar
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
import com.acterics.racesclient.domain.interactor.AddBetUseCase
import com.acterics.racesclient.presentation.addbet.presenter.AddBetPresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_bet.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by root on 07.11.17.
 */
class AddBetFragment: BaseScopedFragment(), AddBetView {


    lateinit var addBetTranslation: AddBetTranslation

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var addBetUseCase: AddBetUseCase

    @Inject
    lateinit var toolbar: Toolbar

    @InjectPresenter
    lateinit var presenter: AddBetPresenter

    @ProvidePresenter
    fun provideAddBetPresenter(): AddBetPresenter =
            AddBetPresenter(router, addBetUseCase)


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
        toolbar.apply {
            setNavigationOnClickListener { presenter.onBack() }
//            postDelayed({setNavigationIcon(R.drawable.ic_close_white)}, 500)
        }
        holderAddBet.setSupportTranslationName(addBetTranslation.addBetHolder)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        toolbar.removeCallbacks(null)
    }

}