package com.acterics.racesclient.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.acterics.racesclient.data.database.entity.HorseEntity
import io.reactivex.Single

/**
 * Created by root on 26.10.17.
 */
@Dao
interface HorseDao {

    @Query("SELECT * FROM horse WHERE id IN " +
            "(SELECT horse_id FROM participant WHERE race_id = :arg0) ORDER BY id")
    fun getRaceHorses(arg0: Long): Single<List<HorseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(horse: HorseEntity)

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insertAll(horses: List<HorseEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllWithConflictIgnore(horses: List<HorseEntity>)
}