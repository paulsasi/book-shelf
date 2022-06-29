package net.paulsasi.bs.http4s.server.handlers

import net.paulsasi.bs.entities.{Book, Topics}

trait BookHandlers {
  
  // Handler for GET /api/web/books
  def getBooks(topic: Option[Topics] = None, authorId: Option[Long] = None): Option[List[Book]]

  // Handler for GET /api/web/book?id=1
  def getBookById(id: Long): Option[Book]
  
  // Handler for DELETE /api/web/book?id=1
  def deleteBookById(id: Long): Unit
  
  // Handler for PUT /api/web/book?name=book1&releaseDate=2021-01-01&topic=SCI_FI&author=1
  def insertBook(book: Book): Unit

  // Handler for PUT /api/web/book?id=1&name=book1&releaseDate=2021-01-01&topic=SCI_FI&author=1
  def updateBook(id: Long, book: Book): Unit

}
