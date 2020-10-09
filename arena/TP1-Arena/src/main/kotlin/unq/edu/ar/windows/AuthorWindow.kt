package unq.edu.ar.windows

import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import unq.edu.ar.appModel.AuthorAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.TextBox
import org.uqbar.commons.model.exceptions.UserException
import org.uqbar.lacar.ui.model.Action
import unq.edu.ar.appModel.DraftNoteAppModel
import unq.edu.ar.appModel.NoteAppModel

class AuthorWindow (owner: WindowOwner, authorAppModel : AuthorAppModel) : SimpleWindow<AuthorAppModel>(owner,authorAppModel) {
    override fun addActions(actionsPanel: Panel?) {
        Button(actionsPanel) with {
            caption = "Add New Note"
            onClick {
                val note = DraftNoteAppModel()
                val view = AddNoteWindow(thisWindow,note)
                view.onAccept {
                    modelObject.addNote(note)
                }
                view.open()
            }
        }

        Button(actionsPanel) with { caption = "Edit Note";
            onClick(Action {
                if (modelObject.selectNote == null){
                    throw UserException("Por favor, selecciona una nota")
                }

                val note = DraftNoteAppModel(modelObject.selectNote!!)
                val view = EditNoteWindow(thisWindow, note)
                view.onAccept {
                        modelObject.editNote(modelObject.selectNote!!.id,note)
                }
                view.open()

            })
        }

        Button(actionsPanel) with {
            caption = "Delete Note"
            onClick {
                if(modelObject.selectNote == null){
                    throw UserException("Please, select a note")
                }
                val deleteNoteWindow = DeleteNoteWindow(thisWindow, modelObject.selectNote!!)
                deleteNoteWindow.onAccept {
                    modelObject.removeNote(modelObject.selectNote!!.id)
                }
                deleteNoteWindow.open()
            }
        }

    }

    override fun createFormPanel(mainPanel: Panel) {
        title = "Author View"
        iconImage = "medium-52-461817.png"

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
                fixedSize = 250
            }

        }
    }



}