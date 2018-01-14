package com.acterics.racesclient.di.profile

import com.acterics.racesclient.presentation.profile.general.ProfileGeneralFragment
import com.acterics.racesclient.presentation.profile.history.view.ProfileHistoryFragment
import dagger.Subcomponent

/**
 * Created by root on 03.11.17.
 */
@ProfileScope
@Subcomponent(modules = [(ProfileModule::class)])
interface ProfileComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): ProfileComponent
    }

    fun inject(profileGeneralFragment: ProfileGeneralFragment)
    fun inject(profileHistoryFragment: ProfileHistoryFragment)

}