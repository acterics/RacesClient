package com.acterics.racesclient.utils.validators

/**
 * Created by root on 02.10.17.
 */
interface Validator {
    fun validate(sequence: CharSequence?): Boolean
}