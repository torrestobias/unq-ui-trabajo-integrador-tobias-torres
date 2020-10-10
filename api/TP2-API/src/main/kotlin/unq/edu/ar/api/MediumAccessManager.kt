package unq.edu.ar.api

import io.javalin.core.security.AccessManager
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse
import org.ui.Author
import unq.edu.ar.Roles
import unq.edu.ar.model.AuthorDoesNotExistException
import unq.edu.ar.model.Medium

class MediumAccessManager (val tokenJWT: MediumTokenJWT, val medium : Medium) : AccessManager {
    override fun manage(handler: Handler, ctx: Context, roles: MutableSet<Role>) {
        val token = ctx.header("Authorization")
        when {
            token == null && roles.contains(Roles.ANYONE) -> handler.handle(ctx)
            token == null -> throw UnauthorizedResponse("Token not found")
            roles.contains(Roles.ANYONE) -> handler.handle(ctx)
            roles.contains(Roles.AUTHOR) -> {
                getAuthor(token)
                handler.handle(ctx)
            }
        }

    }

    fun getAuthor(token : String): Author {
        try {
            val authorId = tokenJWT.validate(token)
            return medium.getAuthorById(authorId)
        }catch (e: TokenNotFound){
            throw UnauthorizedResponse("Token not found")
        }catch (e : AuthorDoesNotExistException){
            throw UnauthorizedResponse("Invalid Token")
        }
    }

}