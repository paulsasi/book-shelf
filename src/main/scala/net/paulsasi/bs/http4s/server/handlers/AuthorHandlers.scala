package net.paulsasi.bs.http4s.server.handlers

import net.paulsasi.bs.entities.Author

trait AuthorHandlers {

  // Hanlder for GET /api/web/author
  def getAuthors(): Option[List[Author]]

  // Handler for GET /api/web/author?id=10
  def getAuthorById(id: Int): Option[Author]

}
