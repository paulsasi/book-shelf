package net.paulsasi.bs.persistence.postgresql

import net.paulsasi.bs.entities.Book
import net.paulsasi.bs.entities.Author
import net.paulsasi.bs.entities.Topics
import net.paulsasi.bs.utils.Date
import net.paulsasi.bs.persistence.api.BookRepository
import net.paulsasi.bs.persistence.postgresql.JdbcPostgresDriver

import java.time.LocalDate
import java.sql.DriverManager
import java.sql.Connection
import scala.collection.mutable.ListBuffer


object BookRepositoryImpl extends BookRepository {

  def getAllBooks(): Option[List[Book]] = {

    val query = "SELECT book.*," +
                        "author.name AS \"author_name\"," +
                        "author.surname AS \"author_surname\"," +
                        "author.nationality AS \"author_nationality\" " +
                "FROM book " +
                "LEFT JOIN author ON author_id=author.id;"
    val result = JdbcPostgresDriver.executeQuery(query)

    var books = ListBuffer[Book]()
    while (result.next()) {
      val author = Author(
                            result.getLong("author_id"),
                            result.getString("author_name"),
                            result.getString("author_surname"),
                            result.getString("author_nationality")
      )
      val book = Book(
                        result.getLong("id"),
                        result.getString("name"),
                        author,
                        Date(LocalDate.now()),
                        Topics.SCI_FI
      )
      books += book
    }

    return Option(books.toList)
  }

//  def getBook(id: Long): Book = {
//    val author = Author(id=1, name="Paul", surname="Sasieta", nationality="Spain")
//    val releaseDate = Date(LocalDate.now)
//    val book = Book(1, "bookName", author, releaseDate, Topic.SCI_FI)
//    return book
//  }
//
//  def insertBook(book: Book): Unit = {
//    ()
//  }
//
//  def deleteBook(id: Long): Unit = {
//    ()
//  }
//
//  def updateBook(book: Book): Unit = {
//    ()
// }
}
