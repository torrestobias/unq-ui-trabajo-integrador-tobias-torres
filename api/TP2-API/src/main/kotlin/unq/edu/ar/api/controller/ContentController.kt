package unq.edu.ar.api.controller

import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.ui.DraftComment
import org.ui.DraftNote
import org.ui.MediumSystem
import org.ui.NotFound
import unq.edu.ar.api.mapper.AuthorMapper
import unq.edu.ar.api.mapper.LatestContentMapper
import unq.edu.ar.api.mapper.NoteMapper
import unq.edu.ar.api.token.MediumTokenJWT

class ContentController (private val mediumSystem: MediumSystem, private val tokenJWT: MediumTokenJWT) {

    fun getLatestContent(ctx : Context){
       val latestContent = mediumSystem.latestAddedNotes().map {
           NoteMapper(it.id,it.title,it.body,it.categories, AuthorMapper(it.author.id,it.author.name),it.comments)
       }
       ctx.status(200)
       ctx.json(
       LatestContentMapper(latestContent)
       )
    }

    fun getContentById(ctx: Context){
        val noteId = ctx.pathParam("id")
            try {
                val note = mediumSystem.getNote(noteId)
                val authorNote = mediumSystem.getAuthor(note.author.id)
                ctx.status(200)
                ctx.json(
                    NoteMapper(note.id,note.title,note.body,note.categories,AuthorMapper(authorNote.id,authorNote.name),note.comments)
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

    fun addCommentNote(ctx: Context){
        val token = ctx.header("Authorization")
        val authorId = tokenJWT.validate(token!!)
        val postId = ctx.pathParam("id")
        val comment = ctx.body<DraftComment>()
        try {
            mediumSystem.addComment(postId,authorId,comment)
            ctx.status(200)
            ctx.json(
                mapOf(
                    "result" to "ok"
                )
            )
        }catch (e: NotFound){
            ctx.status(404)
            ctx.json(
                mapOf(
                    "result" to "error",
                    "message" to "not found Note with id $postId"
                )
            )
        }
    }

    fun searchNote(ctx: Context){
        val textToSearch = ctx.queryParam("text") ?: throw BadRequestResponse("Invalid query - param text is null")
        val searchResults = mediumSystem.searchNotesByTitle(textToSearch).map {
            NoteMapper(it.id,it.title,it.body,it.categories, AuthorMapper(it.author.id,it.author.name),it.comments)
        }
        ctx.status(200)
        ctx.json(
            mapOf(
                "Notes" to searchResults
            )
        )
    }

    fun notesByCategory(ctx: Context){
        val nameOfCategory = ctx.pathParam("name")
        val searchResults = mediumSystem.searchNotesByCategory(nameOfCategory!!).map {
            NoteMapper(it.id,it.title,it.body,it.categories, AuthorMapper(it.author.id,it.author.name),it.comments)
        }
        ctx.status(200)
        ctx.json(
            mapOf(
                "Categories" to searchResults
            )
        )
    }
}