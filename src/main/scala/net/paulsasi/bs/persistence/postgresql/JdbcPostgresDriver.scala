package net.paulsasi.bs.persistence.postgresql

import net.paulsasi.bs.config.BookShelfConfig

import  java.sql.DriverManager

object JdbcPostgresDriver {

  val url: String = BookShelfConfig.persistencePostgresUrl
  val user: String = BookShelfConfig.persistencePostgresUser
  val password: String = BookShelfConfig.persistencePostgresPassword

  @throws(classOf[org.postgresql.util.PSQLException])
  def bootTest(): Unit = {
    val query = "SELECT * FROM author LIMIT 1;"

    val connection = DriverManager.getConnection(this.url, this.user, this.password)
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery(query)
    
    connection.close()
    
  }

  @throws(classOf[org.postgresql.util.PSQLException])
  def executeQuery(query: String): java.sql.ResultSet = {
    val connection = DriverManager.getConnection(this.url, this.user, this.password)
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery(query)

    connection.close()

    return resultSet
  }

  @throws(classOf[org.postgresql.util.PSQLException])
  def execute(query: String): Unit= {
    val connection = DriverManager.getConnection(this.url, this.user, this.password)
    val statement = connection.createStatement()
    val resultSet = statement.execute(query)

    connection.close()
  }
}
