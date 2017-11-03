package com.acterics.racesclient.data.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.acterics.racesclient.domain.model.Organization

/**
 * Created by root on 15.10.17.
 */
@Entity(tableName = "organization")
data class OrganizationEntity(@PrimaryKey
                              @ColumnInfo(name = "id") var id: Long = 0,
                              @ColumnInfo(name = "organization_name") var name: String = ""
)
