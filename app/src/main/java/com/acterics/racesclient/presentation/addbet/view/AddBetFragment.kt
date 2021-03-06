package com.acterics.racesclient.presentation.addbet.view

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.Toolbar
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.acterics.domain.interactor.RaceInteractor
import com.acterics.racesclient.R
import com.acterics.racesclient.common.constants.Extra
import com.acterics.racesclient.common.dsl.addTextChangeListener
import com.acterics.racesclient.common.extentions.setSupportTranslationName
import com.acterics.racesclient.common.ui.fragment.BaseScopedFragment
import com.acterics.racesclient.common.ui.translation.AddBetTranslation
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.presentation.addbet.presenter.AddBetPresenter
import com.acterics.racesclient.presentation.navigation.ToolbarAnimationPresenter
import com.acterics.racesclient.presentation.navigation.ToolbarAnimationView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_add_bet.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by root on 07.11.17.
 */
class AddBetFragment: BaseScopedFragment(), AddBetView, ToolbarAnimationView {

    companion object {
        private const val VALUE_DIF = 5
    }

    lateinit var addBetTranslation: AddBetTranslation

    @Inject lateinit var router: Router
    @Inject lateinit var toolbar: Toolbar
    @Inject lateinit var raceInteractor: RaceInteractor
    @InjectPresenter lateinit var presenter: AddBetPresenter
    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var toolbarAnimationPresenter: ToolbarAnimationPresenter

    private val navigationAvd by lazy {
        ResourcesCompat.getDrawable(resources, R.drawable.avd_close_to_back_white, null) as AnimatedVectorDrawable
    }

    @ProvidePresenter
    fun provideAddBetPresenter(): AddBetPresenter = AddBetPresenter(router, raceInteractor)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let { addBetTranslation = it.getParcelable(Extra.TRANSLATION) }
        sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(Extra.TRANSLATION, addBetTranslation)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_add_bet, container, false)


    override fun injectComponent() {
        ComponentsManager.mainComponent!!.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.apply { setNavigationOnClickListener { presenter.onBack() } }
        holderAddBet.setSupportTranslationName(addBetTranslation.addBetHolder)


        etBet.addTextChangeListener {
            onTextChanged { changed, _, _, _ ->
                changed.takeIf { etBet.hasFocus() }
                        ?.also { if (it.isEmpty()) etBet.setText("0") }
                        ?.takeIf { it.isNotEmpty() }
                        ?.let { it.toString()
                                .toFloat()
                                .also { if (it == 0.0f) etBet.apply { setSelection(0, text.length) } }
                                .times(addBetTranslation.rating)
                                .toString()
                                .let { etResult.setText(it) }
                        }
            }
        }

        etResult.addTextChangeListener {
            onTextChanged { changed, _, _, _ ->
                changed.takeIf { etResult.hasFocus() }
                        ?.also { if (it.isEmpty()) etResult.setText("0") }
                        ?.takeIf { it.isNotEmpty() }
                        ?.let {
                            it.toString()
                                    .toFloat()
                                    .also { if (it == 0.0f) etResult.apply { setSelection(0, text.length) } }
                                    .div(addBetTranslation.rating)
                                    .toString()
                                    .let { etBet.setText(it) }
                        }
            }
        }


        btBetUp.setOnClickListener { presenter.onChangeValue(etBet, { it.plus(VALUE_DIF) } ) }
        btBetDown.setOnClickListener { presenter.onChangeValue(etBet, { it.minus(VALUE_DIF) }) }
        btResultUp.setOnClickListener { presenter.onChangeValue(etResult, { it.plus(VALUE_DIF) } ) }
        btResultDown.setOnClickListener { presenter.onChangeValue(etResult, { it.minus(VALUE_DIF) } ) }
        btAddBet.setOnClickListener { presenter.onAddBet(etBet.text, addBetTranslation.rating, addBetTranslation.participantId) }

    }


    override fun successAdd() {

    }

    override fun errorAdd(throwable: Throwable) {
        Toast.makeText(context, throwable.message, Toast.LENGTH_LONG).show()
    }

    //TODO Add compatibility
    override fun bindNavigationIcon() {
        navigationAvd.reset()
        toolbar.navigationIcon = navigationAvd
    }

    override fun postBindNavigationIcon() {
        toolbar.postDelayed({bindNavigationIcon()}, 500)
    }

    override fun startBackToolbarAnimation() {
        navigationAvd.reset()
        navigationAvd.start()
    }
}