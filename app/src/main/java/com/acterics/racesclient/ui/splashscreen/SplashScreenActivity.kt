package com.acterics.racesclient.ui.splashscreen

import android.os.Bundle
import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.BaseMvpNavigationActivity
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.isAuthenticate
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import ru.terrakok.cicerone.commands.SystemMessage
import javax.inject.Inject

/**
 * Created by root on 27.09.17.
 */
class SplashScreenActivity: BaseMvpNavigationActivity(), SplashScreenView {


    @InjectPresenter
    lateinit var presenter: SplashScreenPresenter

    override fun getBasePresenter(): BaseNavigationPresenter<*> {
        return presenter
    }


    override fun forward(command: Forward) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replace(command: Replace) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun back(command: Back) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun systemMessage(command: SystemMessage) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}