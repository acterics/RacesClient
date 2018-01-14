package com.acterics.racesclient.di.app

import com.acterics.racesclient.BaseApplication
import com.acterics.racesclient.di.auth.AuthenticationComponent
import com.acterics.racesclient.di.main.MainComponent
import com.acterics.racesclient.presentation.editprofile.view.EditProfileActivity
import com.acterics.racesclient.presentation.splash.view.SplashScreenActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by root on 28.09.17.
 */
@Singleton
@Component(modules = [
    (NavigationModule::class),
    (AppModule::class),
    (ApiModule::class),
    (BuildModule::class),
    (NetworkModule::class),
    (DataModule::class)
])
interface AppComponent {
    fun authenticateComponentBuilder(): AuthenticationComponent.Builder
    fun mainComponentBuilder(): MainComponent.Builder

    fun inject(application: BaseApplication)
    fun inject(splashScreenActivity: SplashScreenActivity)
    fun inject(editProfileActivity: EditProfileActivity)

}