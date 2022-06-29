package net.paulsasi.bs.http4s.server.routes

import cats.Monad
import cats.implicits._
import net.paulsasi.bs.http4s.server.handlers.BookHandlersImpl
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.circe.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import org.http4s.*
import net.paulsasi.bs.entities.bookEncoder
import net.paulsasi.bs.entities.Topics
import net.paulsasi.bs.entities.Author
import net.paulsasi.bs.entities.Book
import net.paulsasi.bs.utils.Date
import net.paulsasi.bs.utils.dateFromString
import net.paulsasi.bs.http4s.server.handlers.AuthorHandlersImpl
import org.http4s.dsl.impl.QueryParamDecoderMatcher
import org.http4s.dsl.impl.OptionalQueryParamDecoderMatcher

import java.time.LocalDate
import scala.util.Try


object BookRoutes {

  object IdQueryParamMatcher extends QueryParamDecoderMatcher[Float]("id")

  object NameQueryParamMatcher extends QueryParamDecoderMatcher[String]("name")

  implicit val releaseDateQueryParamDecoder: QueryParamDecoder[Date] = QueryParamDecoder[String].emap(d =>
    Try(dateFromString(d))
      .toEither
      .leftMap(tr => ParseFailure(tr.getMessage, tr.getMessage))
  )

  object ReleaseDateQueryParamMatcher extends QueryParamDecoderMatcher[Date]("releaseDate")

  implicit val topicQueryParamDecoder: QueryParamDecoder[Topics] = QueryParamDecoder[String].emap(t =>
    Try(Topics.valueOf(t))
      .toEither
      .leftMap(tr => ParseFailure(tr.getMessage, tr.getMessage))
  )

  object TopicQueryParamMatcher extends QueryParamDecoderMatcher[Topics]("topic")

  object OptionalTopicQueryParamMatcher extends OptionalQueryParamDecoderMatcher[Topics]("topic")

  object OptionalAuthorQueryParamMatcher extends OptionalQueryParamDecoderMatcher[Long]("author")

  object AuthorQueryParamMatcher extends QueryParamDecoderMatcher[Long]("author")

  def bookRoutes[F[_] : Monad]: HttpRoutes[F] = {
    val dsl = Http4sDsl[F]
    import dsl.*

    HttpRoutes.of[F] {
      case GET -> Root / "web" / "books" :? OptionalTopicQueryParamMatcher(topic) +&
        OptionalAuthorQueryParamMatcher(maybeAuthorId) => {
        BookHandlersImpl.getBooks(topic, maybeAuthorId) match
          case Some(books) => Ok(books.asJson)
          case _ => NotFound(s"No books fetched.")
      }

      case GET -> Root / "web" / "book" :? IdQueryParamMatcher(id) => {
        BookHandlersImpl.getBookById(id.toLong) match
          case Some(book) => Ok(book.asJson)
          case _ => NotFound(s"Book with id $id not found.")
      }

      case DELETE -> Root / "web" / "book" :? IdQueryParamMatcher(id) => {
        BookHandlersImpl.deleteBookById(id.toLong)
        Ok(s"Book with id $id successfully deleted.")
      }

      case PUT -> Root / "web" / "book" :? NameQueryParamMatcher(name) +&
                                              ReleaseDateQueryParamMatcher(releaseDate) +&
                                              TopicQueryParamMatcher(topic) +&
                                              AuthorQueryParamMatcher(authorId) => {

        val author = AuthorHandlersImpl.getAuthorById(authorId.toInt)
        if !(author.isEmpty) then {
          val book = Book(1, name, author.get, releaseDate, topic)
          BookHandlersImpl.insertBook(book)
          Ok(s"Book successfully inserted")
        } else {
          NotFound(s"Author with id $authorId not found")
        }
      }

      case PATCH -> Root / "web" / "book" :? IdQueryParamMatcher(id) +&
                                              NameQueryParamMatcher(name) +&
                                              ReleaseDateQueryParamMatcher(releaseDate) +&
                                              TopicQueryParamMatcher(topic) +&
                                              AuthorQueryParamMatcher(authorId) => {

        val author = AuthorHandlersImpl.getAuthorById(authorId.toInt)

        if !(author.isEmpty) then {
          val book = Book(1, name, author.get, releaseDate, topic)
          BookHandlersImpl.updateBook(id.toLong, book)
          Ok(s"Book with id $id successfully updated")
        } else {
          NotFound(s"Author with id $authorId not found")
        }

      }
    }
  }
}
