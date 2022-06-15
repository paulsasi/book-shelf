package net.paulsasi.bs.persistence.postgresql

import net.paulsasi.bs.entities.Author
import net.paulsasi.bs.persistence.api.AuthorRepository
import net.paulsasi.bs.persistence.api.AuthorPersistenceException
import net.paulsasi.bs.persistence.postgresql.JdbcPostgresDriver

import scala.collection.mutable.ListBuffer

object AuthorRepositoryImpl extends AuthorRepository {

  private val driver: JdbcPostgresDriver = JdbcPostgresDriver(
                                                             "jdbc:postgresql://localhost/book-shelf",
                                                            "book-shelf",
                                                        "book-shelf"
  )

  def getAllAuthors(): Option[List[Author]] = {
    val query = "SELECT * FROM author;"
    val result = this.driver.executeQuery(query)

    var authors = ListBuffer[Author]()
    while (result.next()) {
      val author = Author(
                            result.getLong("id"),
                            result.getString("name"),
                            result.getString("surname"),
                            result.getString("nationality")
      )
      authors += author
      }
    return Option(authors.toList)
  }

  def getAuthor(id: Long): Option[Author] = {

    val query = s"SELECT * FROM author WHERE id=$id;"
    val result = this.driver.executeQuery(query)
    if (result.next()) {
      val author = Author(
        result.getLong("id"),
        result.getString("name"),
        result.getString("surname"),
        result.getString("nationality")
      )
      return Option(author)
    }
    return None
  }

  def insertAuthor(author: Author): Unit = {
    try {
      val query = s"INSERT INTO author(name, surname, nationality)" +
                  s"VALUES ('${author.name}', '${author.surname}', '${author.nationality}');"
      this.driver.execute(query)
    } catch {
      case e: org.postgresql.util.PSQLException => throw AuthorPersistenceException(s"Error inserting author" + e)
    }
  }

  def deleteAuthor(id: Long): Unit = {
    try {
      val query = s"DELETE FROM author WHERE id=$id"
      this.driver.execute(query)
    } catch {
      case e: org.postgresql.util.PSQLException => throw AuthorPersistenceException(s"Error deleting author with" +
                                                                                    s"id $id" + e)
    }
  }

  def updateAuthor(author: Author): Unit = {
    try{
      val query = s"UPDATE author SET name='${author.name}', surname='${author.surname}'," +
                  s"nationality='${author.nationality}' WHERE id=${author.id};"
      this.driver.execute(query)
    } catch {
      case e: org.postgresql.util.PSQLException => throw AuthorPersistenceException(s"Error udpating autor with" +
                                                                                    s"id ${author.id}" + e)
    }
  }
}
