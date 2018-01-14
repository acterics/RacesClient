
package com.acterics.racesserver

import io.javalin.Javalin
fun main(args: Array<String>) {
    val app = Javalin.create()
            .port(8000)
            .start()

    app.get ("/hello") { ctx -> ctx.json("Hello") }

}