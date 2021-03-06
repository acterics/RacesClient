package com.acterics.racesclient.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.acterics.racesclient.data.database.entity.RaceEntity
import com.acterics.racesclient.data.database.entity.relation.RaceWithOrganization
import com.acterics.racesclient.data.database.entity.relation.RaceWithParticipants
import io.reactivex.Single

/**
 * Created by root on 24.10.17.
 */
@Dao
interface RaceDao {

    @Query("SELECT * FROM race WHERE race_date > :arg0 LIMIT :arg1 OFFSET :arg2")
    fun getSchedulePage(arg0: Long, arg1: Int, arg2: Int): Single<RaceWithOrganization>

    @Query("SELECT * FROM race WHERE id = :arg0")
    fun getRace(arg0: Long): Single<RaceWithParticipants>

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insertAll(races: List<RaceEntity>)
}