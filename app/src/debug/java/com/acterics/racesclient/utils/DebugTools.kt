package com.acterics.racesclient.utils

import com.acterics.racesclient.data.entity.User

/**
 * Created by root on 10.10.17.
 */
class DebugTools {
    companion object {
        private val FNAME = "Oleg"
        private val LNAME = "Lipskiy"
        private val EMAIL = "lolego1601@gmail.com"
        private val AVATAR = "https://preview.ibb.co/g0vYow/Sqf5u_Fdh3yo.jpg"
//        private val AVATAR = "https://image.ibb.co/ir41Ab/images.jpg"
    }



    fun getUser(): User {
        return User(FNAME, LNAME, EMAIL, AVATAR)
    }
}