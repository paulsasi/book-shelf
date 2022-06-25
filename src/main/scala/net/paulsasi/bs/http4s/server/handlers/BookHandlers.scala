package net.paulsasi.bs.http4s.server.handlers

import net.paulsasi.bs.entities.{Book, Topics}

class ApiBookException(s: String) extends Exception(s){}

trait BookHandlers {
  
  // Handler for GET /api/web/books
  def getBooks(topic: Option[Topics] = None, authorId: Option[Long] = None): Option[List[Book]]

  // Handler for GET /api/web/book
  def getBookById(id: Long): Option[Book]

}
