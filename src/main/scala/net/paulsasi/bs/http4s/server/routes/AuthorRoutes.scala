package net.paulsasi.bs.http4s.server.routes

import cats.Monad
import org.http4s.{HttpRoutes, QueryParamDecoder}
import org.http4s.dsl.Http4sDsl
import org.http4s.circe.*
import org.http4s.dsl.impl.{OptionalQueryParamDecoderMatcher, QueryParamDecoderMatcher}

import scala.collection.mutable
import io.circe.generic.auto.*
import io.circe.syntax.*
import net.paulsasi.bs.entities.Author
import net.paulsasi.bs.http4s.server.handlers.{ApiAuthorException, AuthorHandlersImpl}


object AuthorRoutes {

  object IdQueryParamMatcher extends QueryParamDecoderMatcher[Float]("id")
  object NameQueryParamMatcher extends QueryParamDecoderMatcher[String]("name")
  object SurnameQueryParamMatcher extends QueryParamDecoderMatcher[String]("surname")
  object NationalityQueryParamMatcher extends OptionalQueryParamDecoderMatcher[String]("nationality")

  def authorRoutes[F[_] : Monad]: HttpRoutes[F] = {
    val dsl = Http4sDsl[F]
    import dsl.*

    HttpRoutes.of[F] {
      case GET -> Root / "web" / "authors" =>
        AuthorHandlersImpl.getAuthors() match
          case Some(authors) => Ok(authors.asJson)
          case _ => NotFound(s"No authors fetched.")

      case GET -> Root / "web" / "author" :? IdQueryParamMatcher(id) =>
        AuthorHandlersImpl.getAuthorById(id.toInt) match {
          case Some(author) => Ok(author.asJson)
          case _ => NotFound(s"No author with id $id found.")
        }

      case DELETE -> Root / "web" / "author" :? IdQueryParamMatcher(id) => {
        AuthorHandlersImpl.deleteAuthorById(id.toInt)
        Ok(s"Author with id $id successfully deleted.")
      }

      case PUT -> Root / "web" / "author" :? NameQueryParamMatcher(name) +&
                                              SurnameQueryParamMatcher(surname) +&
                                              NationalityQueryParamMatcher(maybeNationality) =>
        try {
          val nationality = maybeNationality.getOrElse("")
          AuthorHandlersImpl.insertAuthor(Author(1, name, surname, nationality))
          Ok("Author successfully posted")
        } catch {
          case e: ApiAuthorException => NotFound(s"Error inserting author." + e)
        }

      case PATCH -> Root / "web" / "author" :? IdQueryParamMatcher(id) +&
                                                NameQueryParamMatcher(nameUpdated) +&
                                                SurnameQueryParamMatcher(surnameUpdated) +&
                                                NationalityQueryParamMatcher(maybeNationalityUpdated) =>
        try {
          val nationalityUpdated = maybeNationalityUpdated.getOrElse("")
          AuthorHandlersImpl.updateAuthor(
            id.toInt,
            Author(1, nameUpdated, surnameUpdated, nationalityUpdated)
          )
          Ok("Author successfully patched")
        } catch {
          case e: ApiAuthorException => NotFound(s"Error patching author with name '$nameUpdated' and" +
                                                  s"surname '$surnameUpdated'. " + e)
        }

    }
  }

}
