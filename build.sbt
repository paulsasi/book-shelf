ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.2"

lazy val root = (project in file("."))
  .settings(
    name := "book-shelf"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % Test
