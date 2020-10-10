package unq.edu.ar

import io.javalin.Javalin
import org.ui.bootstrap.getMediumSystem
import unq.edu.ar.model.Medium

fun main(args: Array<String>) {
    val app = Javalin.create().start(7000)
    app.get("/") { ctx -> ctx.result("Hello World") }

    val medium = getMediumSystem()

    app.get("/authors") { ctx ->
        val users = medium.authors
        ctx.json(users)
    }
}