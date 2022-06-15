package net.paulsasi.bs.http4s.server.handlers

import net.paulsasi.bs.entities.Author
import net.paulsasi.bs.http4s.server.handlers.AuthorHandlers

import net.paulsasi.bs.persistence.postgresql.AuthorRepositoryImpl

object AuthorHandlersImpl extends AuthorHandlers {

  override def getAuthors(): Option[List[Author]] = AuthorRepositoryImpl.getAllAuthors()
  override def getAuthorById(id: Int): Option[Author] = AuthorRepositoryImpl.getAuthor(id)

}
