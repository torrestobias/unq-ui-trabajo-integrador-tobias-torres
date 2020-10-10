package unq.edu.ar.api

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import javalinjwt.JWTGenerator
import javalinjwt.JWTProvider
import org.ui.Author
import unq.edu.ar.api.TokenNotFound

class AuthorGenerator : JWTGenerator<Author> {
    override fun generate(author: Author, algorithm: Algorithm): String {
        val token = JWT.create().withClaim("id",author.id)
        return token.sign(algorithm)
    }
}

class MediumTokenJWT {
    val algorithm = Algorithm.HMAC256("top.secret.shhhhh")
    val generator = AuthorGenerator()
    val verifier = JWT.require(algorithm).build()
    val provider = JWTProvider(algorithm, generator, verifier)

    fun generateToken (author: Author) : String{
        return provider.generateToken(author)
    }

    fun validate (token: String): String {
        val validateToken = provider.validateToken(token)
        if(!validateToken.isPresent) throw TokenNotFound()
        return validateToken.get().getClaim("id").asString()
    }
}