package net.paulsasi.bs.http4s.server.handlers

import net.paulsasi.bs.entities.Book
import net.paulsasi.bs.persistence.postgresql.BookRepositoryImpl


object BookHandlersImpl extends BookHandlers {

  override def getBooks(): Option[List[Book]] = BookRepositoryImpl.getAllBooks()

}
