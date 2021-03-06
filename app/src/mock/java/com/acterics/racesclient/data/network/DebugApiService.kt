package com.acterics.racesclient.data.network

import com.acterics.racesclient.common.extentions.getPage
import com.acterics.racesclient.data.network.model.*
import com.acterics.racesclient.data.network.model.request.BetRequest
import com.acterics.racesclient.data.network.model.request.SignInRequest
import com.acterics.racesclient.data.network.model.request.SignUpRequest
import com.acterics.racesclient.data.network.model.response.BaseResponse
import com.acterics.racesclient.data.network.model.response.ScheduleResponse
import io.reactivex.Single
import org.joda.money.Money
import org.joda.time.DateTime
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

/**
 * Created by root on 10.10.17.
 */
class DebugApiService : ApiService {

    companion object {
        private val FNAME = "Oleg"
        private val LNAME = "Lipskiy"
        private val EMAIL = "lolego1601@gmail.com"
        private val AVATAR = "https://preview.ibb.co/g0vYow/Sqf5u_Fdh3yo.jpg"
        private val BALANCE = "USD 100"
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

    private val organizationsPool = ArrayList<OrganizationModel>(ORGANIZATIONS_POOL_SIZE)
    private val racesPool = ArrayList<RaceModel>(RACES_POOL_SIZE)
    private val horsesPool = ArrayList<HorseModel>(HORSE_POOL_SIZE)
    private val user = UserModel(0, FNAME, LNAME, EMAIL, UserInfoModel(AVATAR, Money.parse(BALANCE)))

    private val participationPool = ArrayList<ParticipantModel>()

    init {
        (0..ORGANIZATIONS_POOL_SIZE).forEach { organizationsPool.add(newOrganization()) }
        (0..HORSE_POOL_SIZE).forEach { horsesPool.add(newHorse()) }
        (0..RACES_POOL_SIZE).forEach { racesPool.add(newRace()) }
    }

    override fun signIn(signInRequest: SignInRequest): Single<BaseResponse<UserModel>> {
        Timber.i("signIn: request: $signInRequest")
        return getNetworkSingle(user)
    }

    override fun signUp(signUpRequest: SignUpRequest): Single<BaseResponse<UserModel>> {
        Timber.i("signUp: request: $signUpRequest")
        return getNetworkSingle(user)

    }

    override fun getSchedule(skip: Int, count: Int): Single<BaseResponse<ScheduleResponse>> {
        Timber.i("getSchedule: skip: $skip, count: $count")
        return getNetworkSingle(ScheduleResponse(racesPool.getPage(skip, count)))

    }

    override fun getRace(raceId: Long): Single<BaseResponse<RaceModel>> {
        Timber.i("getRace: raceId: $raceId")
        return getNetworkSingle(racesPool[raceId.toInt()])
    }

    private fun <T> getNetworkSingle(data: T): Single<BaseResponse<T>> {
        return Single.timer(NETWORK_DELAY_MILLS, TimeUnit.MILLISECONDS)
                .map { getSuccessResponse(data) }
                .doOnSuccess { Timber.i("success: $it") }
                .doOnError { Timber.e("error: ${it.message}") }
    }

    private fun <T>getSuccessResponse(data: T): BaseResponse<T> {
        return BaseResponse(0, data)
    }

    override fun addBet(userId: Long, betRequest: BetRequest): Single<BaseResponse<BetModel>> {
        val participantBets = participationPool[betRequest.participantId.toInt()].bets
        val betModel = BetModel(participantBets.size.toLong(), betRequest.bet, betRequest.rating, betRequest.participantId)
        participationPool[betRequest.participantId.toInt()].bets.add(betModel)
        return getNetworkSingle(betModel)
    }

    override fun getHistory(userId: Long, skip: Int, count: Int): Single<BaseResponse<List<HistoryBetModel>>> {
        return getNetworkSingle(getAllHistory().getPage(skip, count))
    }



    private fun getAllHistory(): List<HistoryBetModel> =
        participationPool.flatMap { participant ->
            participant.bets.map { bet -> getNewHistoryBet(participant, bet) }
        }


    private fun getNewHistoryBet(participant: ParticipantModel, bet: BetModel): HistoryBetModel {
        val success = debugRandom.nextBoolean()
        val resultValue = if (success) { bet.bet * bet.rating } else { -bet.bet }
        return HistoryBetModel(bet.id, bet.bet, bet.rating,
                participant.id, participant.horse.name,
                racesPool[participant.raceId.toInt()].dateTime.millis,
                success, resultValue)

    }
    private fun getHorse(): HorseModel {
        return horsesPool[debugRandom.nextInt(HORSE_POOL_SIZE)]
    }


    private fun getRaces(): List<RaceModel> {
        return racesPool
    }

    private fun getOrganization(): OrganizationModel {
        return organizationsPool[debugRandom.nextInt(ORGANIZATIONS_POOL_SIZE)]
    }



    private fun getRace(): RaceModel {
        return racesPool[debugRandom.nextInt(RACES_POOL_SIZE)]
    }


    private fun newRace(): RaceModel {
        val id = racesPool.size
        val title = String.format(RACE_TITLE, id)
        val organizer = getOrganization()
        val date = DateTime.now()
                .plusDays(debugRandom.nextInt(31))
        return RaceModel(id.toLong(), title, date, organizer, newParticipation(id.toLong()))
    }

    private fun newOrganization(): OrganizationModel {
        val id = organizationsPool.size
        return OrganizationModel(id.toLong(), String.format(ORGANIZATION_TITLE, id))
    }

    private fun newHorse(): HorseModel {
        val id = horsesPool.size
        return HorseModel(id.toLong(), "horse$id")
    }

    private fun newParticipation(raceId: Long): List<ParticipantModel> {
        return HashSet<HorseModel>()
                .apply {
                    (0..4)
                            .map { getHorse() }
                            .forEach { add(it) }
                }
                .mapIndexed { index, horseModel -> ParticipantModel((participationPool.size + index).toLong(), raceId,
                        horseModel, newRating(), ArrayList()) }
                .also { participationPool.addAll(it) }
    }


    private fun newRating() = (debugRandom.nextFloat() + 0.01f) * (debugRandom.nextInt(3) + 1)

}

