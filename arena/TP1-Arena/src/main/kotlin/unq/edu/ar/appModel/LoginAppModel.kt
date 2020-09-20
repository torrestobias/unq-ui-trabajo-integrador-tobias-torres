package unq.edu.ar.appModel

import org.ui.Author
import org.ui.MediumSystem
import org.uqbar.commons.model.annotations.Observable

@Observable
class LoginAppModel ()  {
    val medium = MediumAppModel()
    var email : String = ""
    var password : String = ""

    fun login (email : String, password : String):Author {
        return medium.mediumSystem.login(email, password)
    }
}