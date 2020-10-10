package unq.edu.ar.model

import org.ui.Author
import org.ui.bootstrap.getMediumSystem

class Medium {
    val system = getMediumSystem()

    fun login (email : String, password : String) : Author {
        return system.authors.find { it.email == email && it.password == password } ?: throw AuthorDoesNotExistException()
    }


}