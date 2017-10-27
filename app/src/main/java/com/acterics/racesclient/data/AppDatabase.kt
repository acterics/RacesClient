package com.acterics.racesclient.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.acterics.racesclient.data.dao.HorseDao
import com.acterics.racesclient.data.dao.OrganizationDao
import com.acterics.racesclient.data.dao.ParticipantDao
import com.acterics.racesclient.data.dao.RaceDao
import com.acterics.racesclient.data.entity.*

/**
 * Created by root on 24.10.17.
 */
@Database(entities = arrayOf(
        Race::class,
        Horse::class,
        Participant::class,
        Organization::class
        ),
        version = 7, exportSchema = false) abstract class AppDatabase : RoomDatabase() {

    abstract fun raceDao(): RaceDao
    abstract fun horseDao(): HorseDao
    abstract fun participantDao(): ParticipantDao
    abstract fun organizationDao(): OrganizationDao

}