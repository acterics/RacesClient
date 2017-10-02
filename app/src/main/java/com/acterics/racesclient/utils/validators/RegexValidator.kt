package com.acterics.racesclient.utils.validators

/**
 * Created by root on 02.10.17.
 */
abstract class RegexValidator: Validator {

    private val regex by lazy { Regex(getRegexString()) }

    abstract fun getRegexString(): String

    override final fun validate(sequence: CharSequence?): Boolean {
        sequence ?: return false
        return regex.matches(sequence)
    }

}