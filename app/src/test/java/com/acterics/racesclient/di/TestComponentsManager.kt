package com.acterics.racesclient.di

import android.app.Application
import com.acterics.racesclient.di.app.*

/**
 * Created by oleg on 18.01.18.
 */
object TestComponentsManager {

    lateinit var testAppComponent: TestAppComponent


    fun initAppComponent(app: Application) {
        testAppComponent = DaggerTestAppComponent.builder()
                .appModule(AppModule(app))
                .navigationModule(NavigationModule())
                .buildModule(BuildModule())
                .apiModule(ApiModule())
                .dataModule(TestDataModule())
                .build()
    }
}
