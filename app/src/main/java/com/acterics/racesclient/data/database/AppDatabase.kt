package com.acterics.racesclient.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.acterics.racesclient.data.database.dao.*
import com.acterics.racesclient.data.database.entity.*

/**
 * Created by root on 24.10.17.
 */
@Database(entities = arrayOf(
        RaceEntity::class,
        HorseEntity::class,
        ParticipantEntity::class,
        OrganizationEntity::class,
        BetEntity::class
        ),
        version = 9 , exportSchema = false) abstract class AppDatabase : RoomDatabase() {

    abstract fun raceDao(): RaceDao
    abstract fun horseDao(): HorseDao
    abstract fun participantDao(): ParticipantDao
    abstract fun organizationDao(): OrganizationDao
    abstract fun betDao(): BetDao

}