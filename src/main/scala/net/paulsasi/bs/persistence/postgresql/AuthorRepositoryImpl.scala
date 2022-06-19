package net.paulsasi.bs.persistence.postgresql

import net.paulsasi.bs.entities.Author
import net.paulsasi.bs.persistence.api.AuthorRepository
import net.paulsasi.bs.persistence.api.AuthorPersistenceException
import net.paulsasi.bs.persistence.postgresql.JdbcPostgresDriver

import scala.collection.mutable.ListBuffer

object AuthorRepositoryImpl extends AuthorRepository {

  def getAllAuthors(): Option[List[Author]] = {
    val query = "SELECT * FROM author;"
    val result = JdbcPostgresDriver.executeQuery(query)

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
    val result = JdbcPostgresDriver.executeQuery(query)
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
      JdbcPostgresDriver.execute(query)
    } catch {
      case e: org.postgresql.util.PSQLException => throw AuthorPersistenceException(s"Error inserting author. Author " +
                                                                          s"with same name and surname already exists")
    }
  }

  def deleteAuthor(id: Int): Unit = {
    val query = s"DELETE FROM author WHERE id=$id"
    JdbcPostgresDriver.execute(query)
  }

  def updateAuthor(id: Int, author: Author): Unit = {
    try{
      val query = s"UPDATE author " +
                  s"SET name='${author.name}', surname='${author.surname}', nationality='${author.nationality}' " +
                  s"WHERE id='${id}';"
      JdbcPostgresDriver.execute(query)
    } catch {
      case e: org.postgresql.util.PSQLException => throw AuthorPersistenceException(s"Error updating author with " +
                                                                                    s"id $id" + e)
    }
  }
}
