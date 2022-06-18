package net.paulsasi.bs.http4s.server.handlers

import net.paulsasi.bs.entities.Author
import net.paulsasi.bs.http4s.server.handlers.AuthorHandlers
import net.paulsasi.bs.http4s.server.handlers.ApiAuthorException
import net.paulsasi.bs.persistence.api.AuthorPersistenceException
import net.paulsasi.bs.persistence.postgresql.AuthorRepositoryImpl


object AuthorHandlersImpl extends AuthorHandlers {

  override def getAuthors(): Option[List[Author]] = AuthorRepositoryImpl.getAllAuthors()

  override def getAuthorById(id: Int): Option[Author] = AuthorRepositoryImpl.getAuthor(id)

  override def deleteAuthorById(id: Int): Unit = AuthorRepositoryImpl.deleteAuthor(id)

  override def insertAuthor(author: Author): Unit = {
    try {
      AuthorRepositoryImpl.insertAuthor(author)
    } catch {
      case e: AuthorPersistenceException => throw ApiAuthorException(e.toString)
    }
  }
  
  override def updateAuthor(id: Int, authorUpdated: Author): Unit = {
    try {
      AuthorRepositoryImpl.updateAuthor(id, authorUpdated)
    } catch {
      case e: AuthorPersistenceException => throw ApiAuthorException(e.toString)
    }
    
  }

}
