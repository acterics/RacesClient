package com.acterics.racesclient.di

import com.acterics.racesclient.BaseApplication
import com.acterics.racesclient.di.modules.*
import com.acterics.racesclient.ui.auth.AuthenticatePresenter
import com.acterics.racesclient.ui.auth.signin.SignInPresenter
import com.acterics.racesclient.ui.auth.signup.SignUpPresenter
import com.acterics.racesclient.ui.main.MainActivityPresenter
import com.acterics.racesclient.ui.profile.ProfilePresenter
import com.acterics.racesclient.ui.profile.edit.EditProfilePresenter
import com.acterics.racesclient.ui.race.RaceDetailPresenter
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
        ApiModule::class,
        BuildModule::class,
        ValidationModule::class,
        NetworkModule::class
))
interface AppComponent {
    fun inject(application: BaseApplication)
    fun inject(splashScreen: SplashScreenPresenter)
    fun inject(authScreen: AuthenticatePresenter)
    fun inject(signInScreen: SignInPresenter)
    fun inject(signUpScreen: SignUpPresenter)
    fun inject(mainActivityPresenter: MainActivityPresenter)
    fun inject(profileScreen: ProfilePresenter)
    fun inject(editProfileScreen: EditProfilePresenter)
    fun inject(scheduleScreen: SchedulePresenter)
    fun inject(raceDetailScreen: RaceDetailPresenter)
}