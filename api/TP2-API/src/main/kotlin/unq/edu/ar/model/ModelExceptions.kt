package unq.edu.ar.model


import java.lang.Exception

open class AuthorException(message: String) :Exception(message)

class AuthorDoesNotExistException() : AuthorException("User not found")