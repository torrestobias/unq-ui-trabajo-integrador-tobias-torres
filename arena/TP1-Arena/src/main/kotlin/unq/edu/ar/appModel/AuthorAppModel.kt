package unq.edu.ar.appModel

import org.ui.Author
import org.ui.DraftNote
import org.ui.MediumSystem
import org.ui.bootstrap.titles
import org.uqbar.commons.model.annotations.Observable

@Observable
class AuthorAppModel(author: Author, val system : MediumSystem) {

    var id : String = author.id
    var name : String = author.name
    var email : String = author.email
    var password : String = author.password
    var notes = allNotes()
    var selectNote : NoteAppModel? = null
    var photo : String = author.photo
    var search : String = ""
    var notesDuplicates = allNotes()

    fun allNotes() : MutableList<NoteAppModel>{
        var notasDeUser = system.searchNotesByAuthorId(id).map { NoteAppModel(it.id, it.title, it.categories.toString(), it.body) }.toMutableList()
        return notasDeUser
    }

    fun editNote(noteId : String, note : DraftNoteAppModel){
        system.editNote(noteId, DraftNote(note.title,note.body,note.categories))
        notes = allNotes();
    }

    fun removeNote(noteId : String){
        system.removeNote(noteId)
        notes = allNotes()
    }

    fun addNote(note: DraftNoteAppModel){
        system.addNote(id, DraftNote(note.title, note.body, note.categories))
        notes = allNotes()
    }

   fun filterByTitle(){
        notes = allNotes().filter { it.title.contains(search) }.toMutableList()
   }

    fun resetPost() {
        this.notes = notesDuplicates
    }


}