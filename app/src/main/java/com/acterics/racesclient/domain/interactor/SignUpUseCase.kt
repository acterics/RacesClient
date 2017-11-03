package com.acterics.racesclient.domain.interactor

import com.acterics.racesclient.common.extentions.checkNetworkSingle
import com.acterics.racesclient.data.database.entity.User
import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.data.network.model.request.SignUpRequest
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import io.reactivex.Single

/**
 * Created by root on 03.11.17.
 */
class SignUpUseCase(private val apiService: ApiService,
                    private val scheduler: ExecutionScheduler): UseCase.AsSingle<User, SignUpRequest>() {
    override fun build(params: SignUpRequest?): Single<User> =
        apiService.signUp(params ?: throw IllegalArgumentException())
                .checkNetworkSingle()
                .map { User(it.id, it.firstName, it.lastName, it.email, it.userInfo.avatar) }
                .compose(scheduler.highPrioritySingle())

}