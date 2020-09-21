package unq.edu.ar.appModel

import org.ui.Author
import org.uqbar.commons.model.annotations.Observable

@Observable
class AuthorAppModel(author: Author, val mediumAppModel: MediumAppModel) {

    var id : String = author.id
    var name : String = author.name
    var email : String = author.email
    var password : String = author.password
    var notes = allNotes()
    var selectNote : NoteAppModel? = null
    var photo : String = author.photo

    fun allNotes() : MutableList<NoteAppModel>{
        var notasDeUser = mediumAppModel.mediumSystem.searchNotesByAuthorId(id).map { NoteAppModel(it.id, it.title, it.categories, it.body, it.author) }.toMutableList()
        return notasDeUser
    }

    
}