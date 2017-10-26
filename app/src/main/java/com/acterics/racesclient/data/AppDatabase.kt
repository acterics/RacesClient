package com.acterics.racesclient.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.acterics.racesclient.data.dao.RaceDao
import com.acterics.racesclient.data.entity.*

/**
 * Created by root on 24.10.17.
 */
@Database(entities = arrayOf(
        Race::class),
        version = 1, exportSchema = false) abstract class AppDatabase : RoomDatabase() {

    abstract fun raceDao(): RaceDao

}