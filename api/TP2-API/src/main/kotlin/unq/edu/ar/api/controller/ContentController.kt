package unq.edu.ar.api.controller

import io.javalin.http.Context
import org.ui.MediumSystem
import org.ui.NotFound
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
        val noteId = ctx.pathParam("noteId")
            try {
                val note = mediumSystem.getNote(noteId)
                val authorNote = mediumSystem.getAuthor(note.author.id)
                ctx.status(200)
                ctx.json(
                    LatestNoteMapper(note.id,note.title,note.body,note.categories,AuthorMapper(authorNote.id,authorNote.name),note.comments)
                )
            }catch (e : NotFound){
                ctx.status(404)
                ctx.json(
                    mapOf(
                        "result" to "error",
                        "message" to e.message
                    )
                )
            }
    }
}