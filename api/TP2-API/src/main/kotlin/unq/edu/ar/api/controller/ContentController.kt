package unq.edu.ar.api.controller

import io.javalin.http.Context
import org.ui.MediumSystem
import unq.edu.ar.api.token.MediumTokenJWT

class ContentController (private val mediumSystem: MediumSystem, private val tokenJWT: MediumTokenJWT) {

    fun getLatestContent(ctx : Context){

    }
}