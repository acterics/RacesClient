package com.acterics.racesclient.di.app

import com.acterics.racesclient.data.mapper.BetMapperTest
import dagger.Component
import javax.inject.Singleton

/**
 * Created by oleg on 18.01.18.
 */
@Singleton
@Component(modules = [
    NavigationModule::class,
    AppModule::class,
    ApiModule::class,
    BuildModule::class,
    NetworkModule::class,
    DataModule::class
])
interface TestAppComponent: AppComponent {
    fun inject(test: BetMapperTest)
}