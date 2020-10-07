package unq.edu.ar.windows

import org.uqbar.arena.kotlin.extensions.caption
import org.uqbar.arena.kotlin.extensions.text
import org.uqbar.arena.kotlin.extensions.with
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import unq.edu.ar.appModel.NoteAppModel

class DeleteNoteWindow (owner: WindowOwner, model: NoteAppModel): Dialog<NoteAppModel>(owner, model) {
    override fun createFormPanel(mainPanel: Panel?) {
        Label(mainPanel) with {
            text = "Do you remove '${modelObject.title}' ?"
        }

        Button(mainPanel) with {
            caption = "Accept"
            onClick{
                accept()
            }
        }
        Button(mainPanel) with {
            caption = "Cancel"
            onClick{
                cancel()
            }
        }
    }

}