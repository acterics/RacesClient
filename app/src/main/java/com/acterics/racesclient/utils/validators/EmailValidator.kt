package com.acterics.racesclient.utils.validators

import java.util.regex.Pattern

/**
 * Created by root on 02.10.17.
 */
class EmailValidator: RegexValidator() {

    companion object {
        private val EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    }

    override fun getRegexString(): String {
        return EMAIL_PATTERN
    }

}