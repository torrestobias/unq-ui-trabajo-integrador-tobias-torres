package unq.edu.ar.windows

import org.ui.Author
import org.ui.MediumSystem
import org.ui.NotFound
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import unq.edu.ar.appModel.LoginAppModel
import org.uqbar.commons.model.exceptions.UserException
import org.uqbar.lacar.ui.model.Action
import unq.edu.ar.appModel.AuthorAppModel
import unq.edu.ar.appModel.MediumAppModel


class LoginWindow (owner: WindowOwner, loginAppModel: LoginAppModel, val mediumAppModel: MediumAppModel): SimpleWindow<LoginAppModel>(owner,loginAppModel) {
    override fun addActions(mainPanel: Panel) {

    }

    override fun createFormPanel(mainPanel: Panel) {
        title = "Medium"

        Label(mainPanel) with {
            text = "Email"
            alignCenter()
        }

        TextBox(mainPanel) with {
            width = 250
            bindTo("email")
        }

        Label(mainPanel) with {
            text = "Password"
            alignCenter()
        }

        PasswordField(mainPanel) with {
            bindTo("password")
        }

        Button(mainPanel) with {
            caption = "Login"
            onClick(Action {
                try {
                     val author : Author = modelObject.login(modelObject.email,modelObject.password)

                     thisWindow.close()
                     AuthorWindow(thisWindow, AuthorAppModel(author,mediumAppModel)).open()
                } catch (e: NotFound) {
                    throw UserException(e.message)
                }
            })}}


}