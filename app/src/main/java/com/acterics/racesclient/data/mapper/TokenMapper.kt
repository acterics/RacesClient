package com.acterics.racesclient.data.mapper

import com.acterics.domain.model.Token
import com.acterics.racesclient.data.network.model.response.TokenResponse
import javax.inject.Inject

/**
 * Created by oleg on 21.01.18.
 */
class TokenMapper
@Inject constructor() {

    fun toDomain(from: TokenResponse): Token {
        return Token(
                from.token
        )
    }
}