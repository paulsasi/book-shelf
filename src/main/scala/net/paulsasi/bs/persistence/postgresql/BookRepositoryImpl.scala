package net.paulsasi.bs.persistence.postgresql

import net.paulsasi.bs.entities.Book
import net.paulsasi.bs.entities.Author
import net.paulsasi.bs.entities.Topics
import net.paulsasi.bs.utils.Date
import net.paulsasi.bs.utils.dateFromString
import net.paulsasi.bs.utils.dateToString
import net.paulsasi.bs.persistence.api.BookRepository
import net.paulsasi.bs.persistence.postgresql.JdbcPostgresDriver

import java.time.LocalDate
import java.sql.DriverManager
import java.sql.Connection
import scala.collection.mutable.ListBuffer


object BookRepositoryImpl extends BookRepository {

  override def getAllBooks(topic: Option[Topics] = None, authorId: Option[Long] = None): Option[List[Book]] = {

    var query = "SELECT book.*," +
                        "author.name AS \"author_name\"," +
                        "author.surname AS \"author_surname\"," +
                        "author.nationality AS \"author_nationality\" " +
                "FROM book " +
                "LEFT JOIN author ON author_id=author.id"

    query = (topic, authorId) match
      case (Some(t), Some(id)) => query + s" WHERE topic='${t}' AND author_id='${id}';"
      case (Some(t), None) => query + s" WHERE topic='${t}';"
      case (None, Some(id)) => query + s" WHERE author_id='${id}';"
      case _ => query + ';'

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
                        dateFromString(result.getString("release_date")),
                        Topics.valueOf(result.getString("topic"))
      )
      books += book
    }

    return Option(books.toList)
  }

  override def getBook(id: Long): Option[Book] = {

    val query = s"SELECT book.*," +
                        "author.name AS author_name," +
                        "author.surname AS author_surname," +
                        "author.nationality AS author_nationality " +
                "FROM book " +
                "JOIN author ON book.author_id=author.id " +
                s"WHERE book.id=${id};"
    val result = JdbcPostgresDriver.executeQuery(query)

    if (result.next()) {
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
                        dateFromString(result.getString("release_date")),
                        Topics.valueOf(result.getString("topic"))
      )
      return Option(book)
    }
    return None
  }

  override def deleteBook(id: Long): Unit = {
    val query = s"DELETE FROM book WHERE id=${id};"
    JdbcPostgresDriver.execute(query)
  }

  override def insertBook(book: Book): Unit = {
    val query = s"INSERT INTO book(name,release_date,topic,author_id) " +
                s"VALUES (" +
                          s"'${book.name}'," +
                          s"'${dateToString(book.releaseDate)}'," +
                          s"'${book.topic.toString}'," +
                          s"'${book.author.id}'" +
                s");"

    println("################")
    println(query)
    JdbcPostgresDriver.execute(query)
  }

  override def updateBook(id: Long, book: Book): Unit = {

    val query = s"UPDATE book " +
                s"SET " +
                      s"name='${book.name}'," +
                      s"release_date='${dateToString(book.releaseDate)}'," +
                      s"topic='${book.topic.toString}'," +
                      s"author_id=${book.author.id} " +
                s"WHERE id=${id};"

    println(query)
    JdbcPostgresDriver.execute(query)

  }

}