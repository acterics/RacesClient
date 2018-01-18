package com.acterics.racesclient.data.network.model.request

import com.google.gson.annotations.SerializedName

/**
 * Created by root on 19.10.17.
 */
data class SignUpRequest(

        @SerializedName("first_name")
        val firstName: String,

        @SerializedName("last_name")
        val lastName: String,

        val username: String,
        val email: String,
        val password: String
)