package com.acterics.racesclient.presentation.profile.view

import com.acterics.domain.model.User
import com.acterics.racesclient.presentation.profile.ProfileViewModel
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by root on 09.10.17.
 */
interface ProfileView: MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showUser(user: User)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun applyTheme(profileViewModel: ProfileViewModel)
}