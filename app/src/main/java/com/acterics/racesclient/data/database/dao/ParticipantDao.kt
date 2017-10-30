package com.acterics.racesclient.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.acterics.racesclient.data.database.entity.ParticipantEntity
import io.reactivex.Single

/**
 * Created by root on 26.10.17.
 */
@Dao
interface ParticipantDao {

    @Query("SELECT * FROM participant WHERE race_id = :arg0 ORDER BY horse_id")
    fun getRaceParticipants(arg0: Long): Single<List<ParticipantEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(participant: ParticipantEntity)

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insertAll(participants: List<ParticipantEntity>)
}