package com.acterics.racesclient.data.network.model

import com.acterics.racesclient.data.database.entity.Horse

/**
 * Created by root on 21.10.17.
 */
data class HorseModel(val id: Long,
                      val name: String): EntityWrapper<Horse> {
    override fun map(): Horse = Horse(id, name)

}