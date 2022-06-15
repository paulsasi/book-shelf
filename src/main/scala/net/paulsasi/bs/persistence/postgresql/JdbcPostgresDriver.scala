package net.paulsasi.bs.persistence.postgresql

import  java.sql.DriverManager

class JdbcPostgresDriver (url: String, user: String, password: String) {

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
