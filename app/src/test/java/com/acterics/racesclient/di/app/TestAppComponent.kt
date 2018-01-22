package com.acterics.racesclient.di.app

import com.acterics.racesclient.data.mapper.BetMapperTest
import com.acterics.racesclient.data.mapper.HorseMapperTest
import com.acterics.racesclient.data.mapper.OrganizationMapperTest
import com.acterics.racesclient.data.repository.BetRepositoryImplTest
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
    fun inject(test: HorseMapperTest)
    fun inject(test: OrganizationMapperTest)
    fun inject(test: BetRepositoryImplTest)
}