package com.acterics.domain.model

/**
 * Created by oleg on 21.01.18.
 */
data class User(
        var id: Long = 0L,
        var firstName: String = "",
        var lastName: String = "",
        var email: String = "",
        var avatar: String = ""
)