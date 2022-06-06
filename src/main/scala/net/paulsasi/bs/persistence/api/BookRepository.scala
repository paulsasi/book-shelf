package net.paulsasi.bs.persistence.api

import net.paulsasi.bs.entities.Book

trait BookRepository {

  def getAllBooks(): List[Book]

  def getBook(id: Long): Book

  def insertBook(book: Book): Unit

  def deleteBook(id: Long): Unit

  def updateBook(book: Book): Unit
}
