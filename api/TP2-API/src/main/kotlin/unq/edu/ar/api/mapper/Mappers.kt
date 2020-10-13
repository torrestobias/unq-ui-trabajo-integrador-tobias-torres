package unq.edu.ar.api.mapper

import org.ui.Note

data class AuthorLoginMapper(val email: String?, val password : String?)

data class AuthorRegisterMapper(val name: String? = null, val email: String? = null, val password: String? = null,
                                val photo : String? = null)

data class AuthorInformation(val name:String?, val email: String?, val photo: String?)

data class NotesOfUser(val notes: List<Note>)