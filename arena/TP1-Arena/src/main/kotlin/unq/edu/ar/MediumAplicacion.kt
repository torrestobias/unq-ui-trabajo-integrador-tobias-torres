package unq.edu.ar

import org.ui.MediumSystem
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window
import unq.edu.ar.appModel.LoginAppModel
import unq.edu.ar.appModel.MediumAppModel
import unq.edu.ar.windows.LoginWindow

class MediumAplicacion() : Application() {

    override fun createMainWindow(): Window<*> {
        val modelo = MediumAppModel()
        return LoginWindow(this, LoginAppModel(),modelo)
    }
}

fun main(){
    MediumAplicacion().start()
}