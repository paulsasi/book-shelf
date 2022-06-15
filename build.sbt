ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.2"

lazy val root = (project in file("."))
  .settings(
    name := "book-shelf"
  )

val Http4sVersion = "0.23.12"
val CirceVersion = "0.15.0-M1"
val LogbackVersion = "1.2.11"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % Test
libraryDependencies += "org.postgresql" % "postgresql" % "42.1.1"
libraryDependencies += "org.http4s" %% "http4s-blaze-server" % Http4sVersion
libraryDependencies += "org.http4s" %% "http4s-dsl" % Http4sVersion
libraryDependencies += "org.http4s" %% "http4s-circe" % Http4sVersion
libraryDependencies += "io.circe" %% "circe-core" % CirceVersion
libraryDependencies += "io.circe" %% "circe-generic" % CirceVersion
libraryDependencies += "io.circe" %% "circe-parser" % CirceVersion
libraryDependencies += "ch.qos.logback" % "logback-classic" % LogbackVersion
