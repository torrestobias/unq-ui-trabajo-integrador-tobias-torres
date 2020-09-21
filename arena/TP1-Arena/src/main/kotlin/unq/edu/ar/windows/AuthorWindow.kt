package unq.edu.ar.windows

import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import unq.edu.ar.appModel.AuthorAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.TextBox
import unq.edu.ar.appModel.NoteAppModel

class AuthorWindow (owner: WindowOwner, authorAppModel : AuthorAppModel) : SimpleWindow<AuthorAppModel>(owner,authorAppModel) {
    override fun addActions(actionsPanel: Panel?) {
        Button(actionsPanel) with {
            caption = "Add New Note"
        }

        Button(actionsPanel) with {
            caption = "Edit Note"
        }

        Button(actionsPanel) with {
            caption = "Delete Note"
        }


    }

    override fun createFormPanel(mainPanel: Panel) {
        title = "Author View"

        TextBox(mainPanel) with {
            width= 20

            Button(mainPanel) with {
                caption = "Search"
                width = 20

            }
        }

        table<NoteAppModel>(mainPanel){
            bindItemsTo("notes")
            bindSelectionTo("selectNote")
            visibleRows = 15

            column {
                title = "#"
                bindContentsTo("id")
                fixedSize = 100
            }

            column {
                title = "Title"
                bindContentsTo("title")
                fixedSize = 450
            }

        }
    }



}