package net.paulsasi.bs.http4s.server.handlers

import net.paulsasi.bs.entities.Book

class ApiBookException(s: String) extends Exception(s){}

trait BookHandlers {
  
  // Handler for GET /api/web/books
  def getBooks(): Option[List[Book]]

}
