package unq.edu.ar.appModel

import org.ui.Author
import org.uqbar.commons.model.annotations.Observable

@Observable
class NoteAppModel(var id : String, var title : String, var categories : String, var body : String) {
}