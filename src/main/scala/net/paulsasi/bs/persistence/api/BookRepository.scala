package net.paulsasi.bs.persistence.api

import net.paulsasi.bs.entities.Book

class BookPersistenceException(s: String) extends Exception(s){}

trait BookRepository {

  def getAllBooks(): Option[List[Book]]

//  def getBook(id: Long): Book
//
//  def insertBook(book: Book): Unit
//
//  def deleteBook(id: Long): Unit
//
//  def updateBook(book: Book): Unit
}
