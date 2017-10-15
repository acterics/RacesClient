package com.acterics.racesclient.di

import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.di.modules.AppModule
import com.acterics.racesclient.di.modules.NavigationModule
import com.acterics.racesclient.di.modules.NetworkModule
import com.acterics.racesclient.di.modules.ValidationModule
import com.acterics.racesclient.ui.auth.AuthenticatePresenter
import com.acterics.racesclient.ui.auth.signin.SignInPresenter
import com.acterics.racesclient.ui.auth.signup.SignUpPresenter
import com.acterics.racesclient.ui.main.MainActivityPresenter
import com.acterics.racesclient.ui.profile.ProfilePresenter
import com.acterics.racesclient.ui.profile.edit.EditProfilePresenter
import com.acterics.racesclient.ui.schedule.SchedulePresenter
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
        ValidationModule::class,
        NetworkModule::class
))
interface AppComponent {
    fun inject(application: RacesApplication)
    fun inject(splashScreen: SplashScreenPresenter)
    fun inject(authScreen: AuthenticatePresenter)
    fun inject(signInScreen: SignInPresenter)
    fun inject(signUpScreen: SignUpPresenter)
    fun inject(mainActivityPresenter: MainActivityPresenter)
    fun inject(profileScreen: ProfilePresenter)
    fun inject(editProfileScreen: EditProfilePresenter)
    fun inject(scheduleScreen: SchedulePresenter)
}