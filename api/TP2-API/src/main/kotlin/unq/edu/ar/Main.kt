package unq.edu.ar

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.core.security.Role
import io.javalin.core.util.RouteOverviewPlugin
import org.ui.bootstrap.getMediumSystem
import io.javalin.apibuilder.ApiBuilder.*
import unq.edu.ar.api.MediumAccessManager
import unq.edu.ar.api.MediumTokenJWT
import unq.edu.ar.api.controller.AuthorController

import unq.edu.ar.model.Medium


enum class Roles : Role {
    ANYONE, AUTHOR
}

fun main(args: Array<String>) {
    val medium = Medium()
    val jwtToken = MediumTokenJWT()
    val jwtAccessManager = MediumAccessManager(jwtToken,medium)
    val authorController = AuthorController(medium,jwtToken)

    val app = Javalin.create {
        it.defaultContentType = "application/json"
        it.registerPlugin(RouteOverviewPlugin("/routes"))
        it.enableCorsForAllOrigins()
        it.accessManager(jwtAccessManager)
    }

    app.before {
        it.header("Access-Control-Expose-Headers","*")
    }

    app.start(7000)

    app.routes {
        path("login"){
            post(authorController::login, mutableSetOf<Role>(Roles.ANYONE))
        }
    }
}