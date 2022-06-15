package net.paulsasi.bs.http4s.server.routes

import cats.Monad
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.circe._
import org.http4s.dsl.impl.QueryParamDecoderMatcher
import scala.collection.mutable
import io.circe.generic.auto._
import io.circe.syntax._

import net.paulsasi.bs.entities.Author
import net.paulsasi.bs.http4s.server.handlers.AuthorHandlersImpl


object AuthorRoutes {

  object IdQueryParamMatcher extends QueryParamDecoderMatcher[Float]("id")

  // GET /api/web/author?id=10
  def authorRoutes[F[_] : Monad]: HttpRoutes[F] = {
    val dsl = Http4sDsl[F]
    import dsl.*

    HttpRoutes.of[F] {
      case GET -> Root / "web" / "author" =>
        AuthorHandlersImpl.getAuthors() match
          case Some(authors) => Ok(authors.asJson)
          case _ => NotFound(s"No authors fetched.")

      case GET -> Root / "web" / "author" :? IdQueryParamMatcher(id) =>
        AuthorHandlersImpl.getAuthorById(id.toInt) match {
          case Some(author) => Ok(author.asJson)
          case _ => NotFound(s"No author with id $id found.")
        }
    }
  }

}
