package com.acterics.racesclient.domain.interactor

import android.content.Context
import com.acterics.racesclient.common.extentions.checkNetworkSingle
import com.acterics.racesclient.common.extentions.login
import com.acterics.racesclient.common.extentions.saveToken
import com.acterics.racesclient.data.database.entity.User
import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.data.network.model.request.SignUpRequest
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import io.reactivex.Single

/**
 * Created by root on 03.11.17.
 */
class SignUpUseCase(private val apiService: ApiService,
                    private val scheduler: ExecutionScheduler,
                    private val context: Context): UseCase.AsSingle<UseCase.None, SignUpRequest>() {
    override fun build(params: SignUpRequest?): Single<None> = apiService
            .signUp(params ?: throw IllegalArgumentException())
                .checkNetworkSingle()
                .doOnSuccess { context.saveToken(it.token) }
                .flatMap { apiService.getUser() }
                .checkNetworkSingle()
                .map { User(it.id, it.firstName, it.lastName, it.email, it.details.avatar ?: "") }
                .doOnSuccess { context.login(it) }
                .map { UseCase.None() }
                .compose(scheduler.highPrioritySingle())
}