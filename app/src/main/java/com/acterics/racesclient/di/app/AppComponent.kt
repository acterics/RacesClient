package com.acterics.racesclient.di.app

import com.acterics.racesclient.BaseApplication
import com.acterics.racesclient.di.auth.AuthenticationComponent
import com.acterics.racesclient.di.main.MainComponent
import com.acterics.racesclient.di.modules.*
import com.acterics.racesclient.ui.profile.edit.EditProfilePresenter
import com.acterics.racesclient.ui.splashscreen.SplashScreenPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by root on 28.09.17.
 */
@Singleton
@Component(modules = arrayOf(
        NavigationModule::class,
        AppModule::class,
        ApiModule::class,
        BuildModule::class,
        NetworkModule::class
))
interface AppComponent {
    fun authenticateComponentBuilder(): AuthenticationComponent.Builder
    fun mainComponentBuilder(): MainComponent.Builder

    fun inject(application: BaseApplication)
    fun inject(splashScreen: SplashScreenPresenter)


    fun inject(editProfileScreen: EditProfilePresenter)

}