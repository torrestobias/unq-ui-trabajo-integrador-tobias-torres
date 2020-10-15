package unq.edu.ar.api.controller

import io.javalin.http.Context
import org.ui.MediumSystem
import unq.edu.ar.api.mapper.AuthorMapper
import unq.edu.ar.api.mapper.LatestContentMapper
import unq.edu.ar.api.mapper.LatestNoteMapper
import unq.edu.ar.api.token.MediumTokenJWT

class ContentController (private val mediumSystem: MediumSystem, private val tokenJWT: MediumTokenJWT) {

    fun getLatestContent(ctx : Context){
       val latestContent = mediumSystem.latestAddedNotes().map {
           LatestNoteMapper(it.id,it.title,it.body,it.categories, AuthorMapper(it.author.id,it.author.name),it.comments)
       }
       ctx.status(200)
       ctx.json(
       LatestContentMapper(latestContent)
       )
    }

    fun getContentById(ctx: Context){
        val idContent = ctx.pathParam("contentId")

    }
}