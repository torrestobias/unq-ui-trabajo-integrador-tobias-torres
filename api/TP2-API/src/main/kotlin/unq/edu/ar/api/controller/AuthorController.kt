package unq.edu.ar.api.controller

import io.javalin.http.Context
import org.ui.MediumSystem
import org.ui.NotFound
import org.ui.UsedEmail
import unq.edu.ar.api.mapper.AuthorInformation
import unq.edu.ar.api.mapper.AuthorLoginMapper
import unq.edu.ar.api.mapper.AuthorRegisterMapper
import unq.edu.ar.api.mapper.NotesOfUser
import unq.edu.ar.api.token.MediumTokenJWT

class AuthorController ( private val mediumSystem: MediumSystem, private val tokenJWT: MediumTokenJWT) {

    fun login (ctx : Context){
        val loginAuthor = ctx.bodyValidator<AuthorLoginMapper>()
            .check(
                {it.email != null && it.password != null},
                    "Invalid Body"
            )
            .get()

        try {
            val author = mediumSystem.login(loginAuthor.email!!,loginAuthor.password!!)
            ctx.header("Authorization", tokenJWT.generateToken(author))
            ctx.status(200)
            ctx.json(
                mapOf(
                    "result" to "ok"
                )
            )
        }
        catch (e: NotFound){
            ctx.status(404)
            ctx.json(
                mapOf(
                    "result" to "error",
                    "message" to e.message.toString()
                )
            )
        }
    }

    fun register(ctx: Context){
        try {
            val newAuthor = ctx.bodyValidator<AuthorRegisterMapper>()
                .check(
                    {
                        it.name!!.isNotBlank() && it.email!!.isNotBlank() && it.password!!.isNotBlank() && it.photo!!.isNotBlank()
                    },
                    "Invalid Body: Please Complete all fields"
                )
                .get()
            val author = mediumSystem.registerAuthor(newAuthor.name!!,newAuthor.email!!,newAuthor.password!!,newAuthor.photo!!)
            ctx.header("Authorization",tokenJWT.generateToken(author))
            ctx.status(201)
            ctx.json(
                mapOf(
                    "result" to "ok"
                )
            )
        }catch (e : UsedEmail){
            ctx.status(404)
            ctx.json(
                mapOf(
                    "result" to "error",
                    "message" to e.message
                )
            )
        }
    }

    fun getUser(ctx: Context){
        val token = ctx.header("Authorization")
        val authorId = tokenJWT.validate(token!!)
        val author = mediumSystem.getAuthor(authorId)
        ctx.status(201)
        ctx.json(
            AuthorInformation(author.name,author.email,author.photo)
        )
    }

    fun getNotesUser(ctx: Context){
        val token = ctx.header("Authorization")
        val authorId = tokenJWT.validate(token!!)
        val notes = mediumSystem.searchNotesByAuthorId(authorId)
        ctx.status(201)
        ctx.json(
            NotesOfUser(notes)
        )
    }




}