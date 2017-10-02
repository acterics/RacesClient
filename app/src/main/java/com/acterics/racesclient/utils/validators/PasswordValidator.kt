package com.acterics.racesclient.utils.validators

/**
 * Created by root on 02.10.17.
 */

class PasswordValidator: RegexValidator() {

    companion object {
        private val PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*[0-9]).{6,}"
    }
    override fun getRegexString(): String {
        return PASSWORD_PATTERN
    }


}