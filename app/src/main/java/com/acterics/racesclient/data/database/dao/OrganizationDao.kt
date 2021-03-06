package com.acterics.racesclient.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.acterics.racesclient.data.database.entity.OrganizationEntity

/**
 * Created by root on 27.10.17.
 */
@Dao
interface OrganizationDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(organizations: List<OrganizationEntity>)
}