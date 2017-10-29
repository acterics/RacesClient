package com.acterics.racesclient.di

import android.app.Application
import com.acterics.racesclient.common.extentions.notNullLazy
import com.acterics.racesclient.di.app.AppComponent
import com.acterics.racesclient.di.app.AppModule
import com.acterics.racesclient.di.modules.ApiModule
import com.acterics.racesclient.di.modules.BuildModule
import com.acterics.racesclient.di.app.NavigationModule

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

    var mainComponent by notNullLazy {
        appComponent
                .mainComponentBuilder()
                .build()
    }


    fun initAppComponent(app: Application) {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .navigationModule(NavigationModule())
                .buildModule(BuildModule())
                .apiModule(ApiModule())
                .build()
    }

    fun clearAuthenticationComponent() {
        authenticationComponent = null
    }

    fun clearMainComponent() {
        mainComponent = null
    }




}