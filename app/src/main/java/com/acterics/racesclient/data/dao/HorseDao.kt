package com.acterics.racesclient.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.acterics.racesclient.data.entity.Horse
import io.reactivex.Single

/**
 * Created by root on 26.10.17.
 */
@Dao
interface HorseDao {

    @Query("SELECT * FROM horse WHERE id IN " +
            "(SELECT horse_id FROM participant WHERE race_id = :arg0)")
    fun getRaceHorses(arg0: Long): Single<List<Horse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(horse: Horse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(horses: List<Horse>)
}