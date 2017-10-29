package com.acterics.racesclient.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.acterics.racesclient.data.database.dao.HorseDao
import com.acterics.racesclient.data.database.dao.OrganizationDao
import com.acterics.racesclient.data.database.dao.ParticipantDao
import com.acterics.racesclient.data.database.dao.RaceDao
import com.acterics.racesclient.data.database.entity.Horse
import com.acterics.racesclient.data.database.entity.Organization
import com.acterics.racesclient.data.database.entity.Participant
import com.acterics.racesclient.data.database.entity.Race

/**
 * Created by root on 24.10.17.
 */
@Database(entities = arrayOf(
        Race::class,
        Horse::class,
        Participant::class,
        Organization::class
        ),
        version = 8 , exportSchema = false) abstract class AppDatabase : RoomDatabase() {

    abstract fun raceDao(): RaceDao
    abstract fun horseDao(): HorseDao
    abstract fun participantDao(): ParticipantDao
    abstract fun organizationDao(): OrganizationDao

}