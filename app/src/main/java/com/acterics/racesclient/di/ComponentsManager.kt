package com.acterics.racesclient.di

import android.app.Application
import com.acterics.racesclient.common.extentions.notNullLazy
import com.acterics.racesclient.di.app.*
import com.acterics.racesclient.di.main.MainComponent
import com.acterics.racesclient.di.main.MainModule
import com.acterics.racesclient.presentation.main.view.MainActivity

/**
 * Created by root on 29.10.17.
 */
object ComponentsManager {

    lateinit var appComponent: AppComponent

    var authenticationComponent by notNullLazy {
        appComponent
                .authenticateComponentBuilder()
                .build()
    }

    var mainComponent: MainComponent? = null

    var profileComponent by notNullLazy {
        mainComponent
                ?.profileComponentBuilder()
                ?.build()
    }


    fun initAppComponent(app: Application) {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .navigationModule(NavigationModule())
                .buildModule(BuildModule())
                .apiModule(ApiModule())
                .dataModule(DataModule())
                .build()
    }

    fun initMainComponent(mainActivity: MainActivity) {
        mainComponent = appComponent
                .mainComponentBuilder()
                .requestMainModule(MainModule(mainActivity))
                .build()
    }

    fun clearAuthenticationComponent() {
        authenticationComponent = null
    }

    fun clearMainComponent() {
        mainComponent = null
        profileComponent = null
    }

    fun clearProfileComponent() {
        profileComponent = null
    }




}