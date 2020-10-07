package unq.edu.ar

import org.ui.bootstrap.getMediumSystem
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window
import unq.edu.ar.appModel.LoginAppModel
import unq.edu.ar.windows.LoginWindow

class MediumAplicacion() : Application() {

    override fun createMainWindow(): Window<*> {
        var system = getMediumSystem()
        return LoginWindow(this, LoginAppModel(system))
    }
}

fun main(){
    MediumAplicacion().start()
}