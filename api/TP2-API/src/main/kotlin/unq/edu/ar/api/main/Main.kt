package unq.edu.ar.api.main

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.core.security.Role
import io.javalin.core.util.RouteOverviewPlugin
import io.javalin.apibuilder.ApiBuilder.*
import org.ui.bootstrap.getMediumSystem
import unq.edu.ar.api.controller.AuthorController
import unq.edu.ar.api.controller.ContentController
import unq.edu.ar.api.token.MediumTokenJWT


enum class Roles : Role {
    ANYONE, AUTHOR
}

fun main(args: Array<String>) {
    val medium = getMediumSystem()
    val jwtToken = MediumTokenJWT()
    val jwtAccessManager = MediumAccessManager(jwtToken,medium)
    val authorController = AuthorController(medium,jwtToken)
    val contentController = ContentController(medium,jwtToken)

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
        path("register"){
            post(authorController::register, mutableSetOf<Role>(Roles.ANYONE))
        }
        path("user"){
            get(authorController::getUser,mutableSetOf<Role>(Roles.AUTHOR))
            path("notes"){
                get(authorController::getNotesUser,mutableSetOf<Role>(Roles.AUTHOR))
            }
        }
        path("content"){
            get(contentController::getLatestContent,mutableSetOf<Role>(Roles.AUTHOR))
            path(":id"){
                get(contentController::getContentById,mutableSetOf<Role>(Roles.AUTHOR))
                post(contentController::addCommentNote,mutableSetOf<Role>(Roles.AUTHOR))
            }
        }

        path("search"){
            get(contentController::searchNote,mutableSetOf<Role>(Roles.AUTHOR))
        }
        path("category"){
            path(":name"){
                get(contentController::notesByCategory,mutableSetOf<Role>(Roles.AUTHOR))
            }
        }
    }
}