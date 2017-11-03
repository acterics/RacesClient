package com.acterics.racesclient.domain.interactor

import com.acterics.racesclient.common.extentions.checkNetworkSingle
import com.acterics.racesclient.data.database.entity.User
import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.data.network.model.request.SignInRequest
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by root on 26.10.17.
 */
class SignInUseCase
@Inject constructor(private val apiService: ApiService,
                    private val scheduler: ExecutionScheduler): UseCase.AsSingle<User, SignInRequest>() {
    override fun build(params: SignInRequest?): Single<User> =
            apiService.signIn(params ?: throw IllegalArgumentException())
                    .checkNetworkSingle()
                    .map { User(it.id, it.firstName, it.lastName, it.email, it.userInfo.avatar) }
                    .compose(scheduler.highPrioritySingle())

}