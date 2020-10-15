package unq.edu.ar.api.mapper

import org.ui.Comment
import org.ui.Note

data class AuthorLoginMapper(val email: String?,
                             val password : String?)

data class AuthorRegisterMapper(val name: String? = null,
                                val email: String? = null,
                                val password: String? = null,
                                val photo : String? = null)

data class AuthorInformation(val name:String?,
                             val email: String?,
                             val photo: String?)

data class NotesOfUser(val notes: List<Note>)

data class NoteMapper(val id : String,
                      val title : String,
                      val body : String,
                      val categories : List<String>,
                      val author : AuthorMapper,
                      val comments : MutableList<Comment>)

data class AuthorMapper(val id: String?, val name : String?)

data class LatestContentMapper(val latestContent : List<NoteMapper>)
