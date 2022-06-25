package net.paulsasi.bs.persistence.api

import net.paulsasi.bs.entities.{Book, Topics}

class BookPersistenceException(s: String) extends Exception(s){}

trait BookRepository {

  def getAllBooks(topic: Option[Topics] = None, authorId: Option[Long] = None): Option[List[Book]]

  def getBook(id: Long): Option[Book]
//
//  def insertBook(book: Book): Unit
//
//  def deleteBook(id: Long): Unit
//
//  def updateBook(book: Book): Unit
}
