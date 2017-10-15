package com.acterics.racesclient.utils

import com.acterics.racesclient.data.entity.Organization
import com.acterics.racesclient.data.entity.Race
import com.acterics.racesclient.data.entity.User
import org.joda.time.DateTime
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by root on 10.10.17.
 */
class DebugTools {
    companion object {
        private val FNAME = "Oleg"
        private val LNAME = "Lipskiy"
        private val EMAIL = "lolego1601@gmail.com"
        private val AVATAR = "https://preview.ibb.co/g0vYow/Sqf5u_Fdh3yo.jpg"


        private val RACE_TITLE = "Mock race title #%d"
        private val ORGANIZATION_TITLE = "Mock organization #%d"



        private val ORGANIZATIONS_POOL_SIZE = 30
        private val RACES_POOL_SIZE = 200

        private val RACES_PAGE_SIZE = 10


        private val NETWORK_DELAY_MILLS = 1000L
//        private val AVATAR = "https://image.ibb.co/ir41Ab/images.jpg"
    }

    private val debugRandom = Random()

    private val organizationsPool = ArrayList<Organization>(ORGANIZATIONS_POOL_SIZE)
    private val racesPool = ArrayList<Race>(RACES_POOL_SIZE)


    init {
        (0..ORGANIZATIONS_POOL_SIZE).forEach { organizationsPool.add(newOrganization()) }
        (0..RACES_POOL_SIZE).forEach { racesPool.add(newRace()) }
    }

    fun getNetworkDelay(): Long {
        return NETWORK_DELAY_MILLS
    }

    fun getUser(): User {
        return User(FNAME, LNAME, EMAIL, AVATAR)
    }

    fun getRacesPage(page: Int): List<Race> {
        val start = RACES_PAGE_SIZE * (page)
        return if (start + RACES_PAGE_SIZE < racesPool.size) {
            racesPool.subList(start, start + RACES_PAGE_SIZE)
        } else {
            ArrayList()
        }
    }

    fun getRaces(): List<Race> {
        return racesPool
    }

    fun getOrganization(): Organization {
        return organizationsPool[debugRandom.nextInt(ORGANIZATIONS_POOL_SIZE)]
    }

    fun getRace(): Race {
        return racesPool[debugRandom.nextInt(RACES_POOL_SIZE)]
    }


    private fun newRace(): Race {

        val title = String.format(RACE_TITLE, racesPool.size)
        val organizer = getOrganization()
        val date = DateTime.now()
                .plusDays(debugRandom.nextInt(31))
        return Race(title, organizer, date)
    }

    private fun newOrganization(): Organization {
        return Organization(String.format(ORGANIZATION_TITLE, organizationsPool.size))
    }
}