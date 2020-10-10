package unq.edu.ar.api.controller

import io.javalin.http.Context
import org.ui.MediumSystem
import unq.edu.ar.model.AuthorDoesNotExistException
import unq.edu.ar.model.Medium

class AuthorController ( private val medium : Medium, private val tokenJWT: MediumTokenJWT) {

    fun login (ctx : Context){
        val loginAuthor = ctx.bodyValidator<AuthorLoginMapper>()
            .check(
                {it.email != null && it.password != null},
                    "Invalid Body"
            )
            .get()

        try {
            val author = medium.login(loginAuthor.email!!,loginAuthor.password!!)
            ctx.header("Authorization", tokenJWT.generateToken(author))
            ctx.status(200)
            ctx.json(
                mapOf(
                    "result" to "ok"
                )
            )
        }
        catch (e: AuthorDoesNotExistException){
            ctx.status(404)
            ctx.json(
                mapOf(
                    "result" to "error",
                    "message" to e.message.toString()
                )
            )
        }
    }







}