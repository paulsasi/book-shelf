package net.paulsasi.bs.boot

import net.paulsasi.bs.config.BookShelfConfig
import net.paulsasi.bs.persistence.postgresql.JdbcPostgresDriver
import net.paulsasi.bs.persistence.api.AuthorPersistenceException

object Boot {

  BookShelfConfig

  try {
    JdbcPostgresDriver.bootTest()
  } catch {
    case e: Throwable => throw new AuthorPersistenceException("Error in database boot test: " + e)
  }

}
