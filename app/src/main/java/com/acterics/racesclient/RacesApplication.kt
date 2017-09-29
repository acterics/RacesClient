package com.acterics.racesclient

import android.app.Application
import com.acterics.racesclient.di.AppComponent
import com.acterics.racesclient.di.DaggerAppComponent
import com.acterics.racesclient.di.modules.AppModule
import com.acterics.racesclient.di.modules.NavigationModule
import javax.inject.Inject

/**
 * Created by root on 28.09.17.
 */
class RacesApplication: Application() {

    @Inject
    lateinit var configuration: ApplicationConfiguration

    companion object {
        lateinit var applicationComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .navigationModule(NavigationModule())
                .build()
        applicationComponent.inject(this)

        configuration.initialize(this)

    }
}