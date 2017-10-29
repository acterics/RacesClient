package com.acterics.racesclient

import android.app.Application
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.di.app.AppComponent
import com.acterics.racesclient.di.DaggerAppComponent
import com.acterics.racesclient.di.app.AppModule
//import com.acterics.racesclient.di.DaggerAppComponent
import com.acterics.racesclient.di.modules.*
import javax.inject.Inject

/**
 * Created by root on 28.09.17.
 */
class BaseApplication : Application() {

    @Inject
    lateinit var configuration: ApplicationConfiguration

    override fun onCreate() {
        super.onCreate()

        ComponentsManager.initAppComponent(this)
        ComponentsManager.appComponent.inject(this)
        configuration.initialize(this)

    }
}