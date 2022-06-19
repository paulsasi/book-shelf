package net.paulsasi.bs.config

import java.io.FileNotFoundException
import java.util.Properties
import scala.io.Source

object BookShelfConfig {

  val url = getClass.getResource("/application.properties")
  val properties: Properties = new Properties()

  url match {
    case null => throw new FileNotFoundException("Properties file cannot be loaded")
    case _ => {
                new Properties()
                properties.load(Source.fromURL(url).bufferedReader())
    }
  }

  val persistencePostgresUrl: String = properties.getProperty("bs.persistence.postgresql.url")
  val persistencePostgresUser: String = properties.getProperty("bs.persistence.postgresql.user")
  val persistencePostgresPassword: String = properties.getProperty("bs.persistence.postgresql.password")

}
