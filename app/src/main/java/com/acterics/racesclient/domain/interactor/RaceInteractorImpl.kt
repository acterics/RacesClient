package com.acterics.racesclient.domain.interactor

import com.acterics.domain.interactor.RaceInteractor
import com.acterics.domain.model.Bet
import com.acterics.domain.model.Participant
import com.acterics.domain.model.Race
import com.acterics.domain.model.dto.Page
import com.acterics.domain.repository.BetRepository
import com.acterics.domain.repository.RaceRepository
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by oleg on 22.01.18.
 */
class RaceInteractorImpl(private val raceRepository: RaceRepository,
                         private val betRepository: BetRepository,
                         private val scheduler: ExecutionScheduler): RaceInteractor {
    override fun getRaceList(page: Page): Single<List<Race>> =
            raceRepository.getSchedulePage(page, false)
                    .compose(scheduler.highPrioritySingle())

    override fun getRaceDetails(raceId: Long): Single<Race> =
            raceRepository.getRaceDetails(raceId, false)
                    .compose(scheduler.highPrioritySingle())

    override fun addBet(bet: Bet, participant: Participant): Completable {
        return betRepository.addBet(bet, participant)
                .compose(scheduler.highPriorityCompletable())
    }


}