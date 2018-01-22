package com.acterics.racesclient.domain.interactor

import com.acterics.domain.interactor.ProfileInteractor
import com.acterics.domain.model.User
import com.acterics.domain.model.dto.HistoryBet
import com.acterics.domain.model.dto.Page
import com.acterics.domain.repository.BetRepository
import com.acterics.domain.repository.UserRepository
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import io.reactivex.Single

/**
 * Created by oleg on 22.01.18.
 */
class ProfileInteractorImpl(private var userRepository: UserRepository,
                            private var betRepository: BetRepository,
                            private var scheduler: ExecutionScheduler): ProfileInteractor {


    override fun getBetHistory(page: Page): Single<List<HistoryBet>> {
        return betRepository.getBetHistory(page, false)
                .compose(scheduler.highPrioritySingle())
    }

    override fun getUser(): Single<User> {
        return userRepository.getUser(false)
                .compose(scheduler.highPrioritySingle())
    }

}