package com.acterics.racesclient.domain.interactor

import com.acterics.racesclient.data.entity.User
import com.acterics.racesclient.data.model.request.SignInRequest
import com.acterics.racesclient.data.rest.ApiService
import com.acterics.racesclient.domain.interactor.UseCase
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.common.extentions.checkNetworkSingle
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by root on 26.10.17.
 */
class Authenticate
@Inject constructor(private val apiService: ApiService,
                    private val scheduler: ExecutionScheduler): UseCase.AsSingle<User, SignInRequest>() {
    override fun build(params: SignInRequest?): Single<User> =
            apiService.signIn(params ?: throw IllegalArgumentException())
                    .checkNetworkSingle()
                    .map { it.map() }
                    .compose(scheduler.highPrioritySingle())

}