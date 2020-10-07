package unq.edu.ar.appModel

import org.uqbar.commons.model.annotations.Observable

@Observable
class DraftNoteAppModel() {
    var title = ""
    var body = ""
    var categories = ""

    constructor(noteAppModel: NoteAppModel): this(){
        title = noteAppModel.title
        body = noteAppModel.body
        categories = noteAppModel.categories
    }
}