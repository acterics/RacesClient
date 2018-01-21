package com.acterics.domain.model.dto

/**
 * Created by oleg on 21.01.18.
 */
data class SignUpCredentials(
        var email: String = "",
        var password: String = "",
        var firstName: String = "",
        var lastName: String = ""
)