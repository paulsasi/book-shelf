package net.paulsasi.bs.http4s.server.handlers

import net.paulsasi.bs.entities.Book
import net.paulsasi.bs.entities.Topics
import net.paulsasi.bs.persistence.postgresql.BookRepositoryImpl


object BookHandlersImpl extends BookHandlers {

  override def getBooks(topic: Option[Topics] = None, authorId: Option[Long] = None): Option[List[Book]] = {
    BookRepositoryImpl.getAllBooks(topic, authorId)
  }

  override def getBookById(id: Long): Option[Book] = BookRepositoryImpl.getBook(id)

}
