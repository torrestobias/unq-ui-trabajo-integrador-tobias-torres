package unq.edu.ar.windows

import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import unq.edu.ar.appModel.AuthorAppModel

class AuthorWindow (owner: WindowOwner, authorAppModel : AuthorAppModel) : SimpleWindow<AuthorAppModel>(owner,authorAppModel) {
    override fun addActions(actionsPanel: Panel?) {

    }

    override fun createFormPanel(mainPanel: Panel?) {

    }

}