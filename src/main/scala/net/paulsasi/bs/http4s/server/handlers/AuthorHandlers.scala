package net.paulsasi.bs.http4s.server.handlers

import net.paulsasi.bs.entities.Author

class ApiAuthorException(s: String) extends Exception(s){}

trait AuthorHandlers {

  // Hanlder for GET /api/web/authors
  def getAuthors(): Option[List[Author]]

  // Handler for GET /api/web/author?id=1
  def getAuthorById(id: Int): Option[Author]

  // Handler for DELETE /api/web/author?id=1
  def deleteAuthorById(id: Int): Unit

  // Hanlder for INSERT /api/web/author?name=paul&surname=sasieta&nationality=spain
  @throws(classOf[ApiAuthorException])
  def insertAuthor(author: Author): Unit

  // Hanlder for PATCH /api/web/author?name=paul&surname=sasieta&name=pepe&surname=seseta
  @throws(classOf[ApiAuthorException])
  def updateAuthor(id: Int, author: Author): Unit
  
}
