package com.acterics.racesclient.utils

import com.acterics.racesclient.data.entity.*
import com.acterics.racesclient.data.model.request.SignInRequest
import com.acterics.racesclient.data.model.request.SignUpRequest
import com.acterics.racesclient.data.model.response.BaseResponse
import com.acterics.racesclient.data.model.response.RaceDetailResponse
import com.acterics.racesclient.data.model.response.ScheduleResponse
import com.acterics.racesclient.data.rest.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTime
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

/**
 * Created by root on 10.10.17.
 */

typealias DebugParticipation = HashMap<Long, Set<Horse>>

class DebugApiService : ApiService {

    companion object {
        private val FNAME = "Oleg"
        private val LNAME = "Lipskiy"
        private val EMAIL = "lolego1601@gmail.com"
        private val AVATAR = "https://preview.ibb.co/g0vYow/Sqf5u_Fdh3yo.jpg"
//        private val AVATAR = "https://image.ibb.co/ir41Ab/images.jpg"

        private val RACE_TITLE = "Mock race title #%d"
        private val ORGANIZATION_TITLE = "Mock organization #%d"



        private val ORGANIZATIONS_POOL_SIZE = 30
        private val RACES_POOL_SIZE = 200
        private val HORSE_POOL_SIZE = 100

        private val RACES_PAGE_SIZE = 10
        private val RACE_PARTICIPATIONS_MAX = 15


        private val NETWORK_DELAY_MILLS = 1000L


    }

    private val debugRandom = Random()

    private val organizationsPool = ArrayList<Organization>(ORGANIZATIONS_POOL_SIZE)
    private val racesPool = ArrayList<Race>(RACES_POOL_SIZE)
    private val horsesPool = ArrayList<Horse>(HORSE_POOL_SIZE)
    private val user = User(0, FNAME, LNAME, EMAIL, AVATAR)

    private val participationPool = DebugParticipation()

    init {
        (0..ORGANIZATIONS_POOL_SIZE).forEach { organizationsPool.add(newOrganization()) }
        (0..HORSE_POOL_SIZE).forEach { horsesPool.add(newHorse()) }

        (0..RACES_POOL_SIZE).forEach {
            racesPool.add(newRace())
            participationPool[racesPool[it].id] = newParticipation()
        }
    }

    override fun signIn(signInRequest: SignInRequest): Observable<BaseResponse<User>> {
        return getNetworkObservable(user)
    }

    override fun signUp(signUpRequest: SignUpRequest): Observable<BaseResponse<User>> {
        return getNetworkObservable(user)

    }

    override fun getSchedule(page: Int): Observable<BaseResponse<ScheduleResponse>> {
        return getNetworkObservable(ScheduleResponse(getRacesPage(page)))

    }

    override fun getRace(raceId: Long): Observable<BaseResponse<RaceDetailResponse>> {
        return getNetworkObservable(getRaceDetails(raceId))
    }

    private fun <T> getNetworkObservable(data: T): Observable<BaseResponse<T>> {
        return Observable.timer(NETWORK_DELAY_MILLS, TimeUnit.MILLISECONDS)
                .map { getSuccessResponse(data) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun <T>getSuccessResponse(data: T): BaseResponse<T> {
        return BaseResponse(0, data)
    }


    private fun getRaceDetails(id: Long): RaceDetailResponse {
        val debugParticipation = participationPool[id] ?: throw IllegalArgumentException()
        val race = racesPool[id.toInt()]
        return RaceDetailResponse(race,
                debugParticipation.map { Participant(it,
                        (debugRandom.nextFloat() + 0.01f) * (debugRandom.nextInt(3) + 1)) })


    }

    private fun getHorse(): Horse {
        return horsesPool[debugRandom.nextInt(HORSE_POOL_SIZE)]
    }

    private fun getRacesPage(page: Int): List<Race> {
        val start = RACES_PAGE_SIZE * (page)
        return if (start + RACES_PAGE_SIZE < racesPool.size) {
            racesPool.subList(start, start + RACES_PAGE_SIZE)
        } else {
            ArrayList()
        }
    }

    private fun getRaces(): List<Race> {
        return racesPool
    }

    private fun getOrganization(): Organization {
        return organizationsPool[debugRandom.nextInt(ORGANIZATIONS_POOL_SIZE)]
    }

    private fun getRace(): Race {
        return racesPool[debugRandom.nextInt(RACES_POOL_SIZE)]
    }


    private fun newRace(): Race {
        val id = racesPool.size
        val title = String.format(RACE_TITLE, id)
        val organizer = getOrganization()
        val date = DateTime.now()
                .plusDays(debugRandom.nextInt(31))
        return Race(id.toLong(), title, organizer, date)
    }

    private fun newOrganization(): Organization {
        val id = organizationsPool.size
        return Organization(id.toLong(), String.format(ORGANIZATION_TITLE, id))
    }

    private fun newHorse(): Horse {
        val id = horsesPool.size
        return Horse(id.toLong(), "horse$id")
    }

    private fun newParticipation(): Set<Horse> {
        return HashSet<Horse>().apply {
            (0..debugRandom.nextInt(RACE_PARTICIPATIONS_MAX))
                    .map { getHorse() }
                    .forEach { add(it) }
        }
    }
}

