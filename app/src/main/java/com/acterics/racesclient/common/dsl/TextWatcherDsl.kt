package com.acterics.racesclient.common.dsl

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

/**
 * Created by oleg on 14.01.18.
 */
fun TextView.addTextChangeListener(init: TextWatcherHelper.() -> Unit) {
    val listener = TextWatcherHelper()
    listener.init()
    this.addTextChangedListener(listener)
}


class TextWatcherHelper: TextWatcher {

    private var afterTextChanged: ((Editable?) -> Unit)? = null

    fun afterTextChanged(onAfterTextChanged: ((Editable?) -> Unit)?) {
        afterTextChanged = onAfterTextChanged
    }

    override fun afterTextChanged(s: Editable?) {
        afterTextChanged?.invoke(s)
    }


    private var beforeTextChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null

    fun beforeTextChanged(onBeforeTextChanged: ((CharSequence?, Int, Int, Int) -> Unit)?) {
        beforeTextChanged = onBeforeTextChanged
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeTextChanged?.invoke(s, start, count, after)
    }


    private var textChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null

    fun onTextChanged(onTextChanged: ((CharSequence?, Int, Int, Int) -> Unit)?) {
        textChanged = onTextChanged
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        textChanged?.invoke(s, start, before, count)
    }

}