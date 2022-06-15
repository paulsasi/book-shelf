package net.paulsasi.bs.persistence.postgresql

import net.paulsasi.bs.entities.Book
import net.paulsasi.bs.entities.Author
import net.paulsasi.bs.entities.Topics
import net.paulsasi.bs.utils.Date
import net.paulsasi.bs.persistence.api.BookRepository

import java.time.LocalDate
import java.sql.DriverManager
import java.sql.Connection


class BookRepositoryImpl(driverc: JdbcPostgresDriver) extends BookRepository {

  val driver: JdbcPostgresDriver = driverc

  def getAllBooks(): List[Book] = {

    val query = ""
    return List[Book]()
  }

  def getBook(id: Long): Book = {
    val author = Author(id=1, name="Paul", surname="Sasieta", nationality="Spain")
    val releaseDate = Date(LocalDate.now)
    val topic = Topics.SCI_FI
    val book = Book(id=1, name="bookName", author=author, releaseDate=releaseDate, topic=topic)
    return book
  }

  def insertBook(book: Book): Unit = {
    ()
  }

  def deleteBook(id: Long): Unit = {
    ()
  }

  def updateBook(book: Book): Unit = {
    ()
  }
}
