package com.acterics.racesclient.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.acterics.racesclient.data.database.entity.BetEntity

/**
 * Created by root on 31.10.17.
 */
@Dao
interface BetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(betEntity: BetEntity)
}