package com.acterics.racesserver.controller

import io.javalin.Context

/**
 * Created by oleg on 21.12.17.
 */
class RacesController {

    fun getRacesPage(context: Context) {
        context.json("Hello")
    }
}