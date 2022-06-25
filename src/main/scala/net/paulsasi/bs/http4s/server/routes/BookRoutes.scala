package net.paulsasi.bs.http4s.server.routes

import cats.Monad
import net.paulsasi.bs.http4s.server.handlers.BookHandlersImpl
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.circe.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import net.paulsasi.bs.entities.bookEncoder
import net.paulsasi.bs.entities.Topics
import org.http4s.dsl.impl.QueryParamDecoderMatcher
import org.http4s.dsl.impl.OptionalQueryParamDecoderMatcher


object BookRoutes {

  object IdQueryParamMatcher extends QueryParamDecoderMatcher[Float]("id")
  object TopicQueryParamMatcher extends OptionalQueryParamDecoderMatcher[String]("topic")
  object AuthorQueryParamMatcher extends OptionalQueryParamDecoderMatcher[Long]("author")

  def bookRoutes[F[_] : Monad]: HttpRoutes[F] = {
    val dsl = Http4sDsl[F]
    import dsl.*

    HttpRoutes.of[F] {
      case GET -> Root / "web" / "books"  :? TopicQueryParamMatcher(maybeTopic) +&
                                              AuthorQueryParamMatcher(maybeAuthorId)=> {
        try {
          val topic: Option[Topics] = maybeTopic match
            case Some(topic) => Option(Topics.valueOf(topic))
            case _ => None

          BookHandlersImpl.getBooks(topic, maybeAuthorId) match
            case Some(books) => Ok(books.asJson)
            case _ => NotFound(s"No books fetched.")
        }
        catch {
          case e: java.lang.IllegalArgumentException => NotFound(s"'${maybeTopic.getOrElse("")}' is not a valid input topic.")
        }
      }

      case GET -> Root / "web" / "book" :? IdQueryParamMatcher(id) => {
        BookHandlersImpl.getBookById(id.toLong) match
          case Some(book) => Ok(book.asJson)
          case _ => NotFound(s"Book with id $id not found.")
      }
    }
    }
}
