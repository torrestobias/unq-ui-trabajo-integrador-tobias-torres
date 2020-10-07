package unq.edu.ar.windows

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action
import unq.edu.ar.appModel.DraftNoteAppModel

class AddNoteWindow (owner: WindowOwner, draftNoteAppModel: DraftNoteAppModel) : Dialog<DraftNoteAppModel>(owner,draftNoteAppModel){
    override fun addActions(actionsPanel: Panel?) {
        Button(actionsPanel) with {
            caption = "Accept"
            onClick(Action { accept() })
        }

        Button(actionsPanel) with {
            caption = "Cancel"
            onClick(Action { cancel() })
        }
    }

    override fun createFormPanel(mainPanel: Panel?) {
        title = "Add New Note Window"
        Label(mainPanel) with {
            bindTo("title")
        }

        Label(mainPanel) with {
            text = "Title"
        }
        TextBox(mainPanel) with {
            bindTo("title")
            width = 200
        }

        Label(mainPanel) with {
            text = "Body"
        }
        TextBox(mainPanel) with {
            bindTo("body")
            width = 200
        }

        Label(mainPanel) with {
            text = "Categories"
        }
        TextBox(mainPanel) with {
            bindTo("categories")
            width = 200
        }

    }



}