package com.acterics.domain.interactor

import com.acterics.domain.model.dto.HistoryBet
import com.acterics.domain.model.dto.Page
import io.reactivex.Single

/**
 * Created by oleg on 21.01.18.
 */
interface ProfileInteractor: Interactor {

    fun getBetHistory(page: Page): Single<List<List<HistoryBet>>>
    fun getUser()
}