package net.paulsasi.bs.http4s.server.routes

import cats.Monad
import net.paulsasi.bs.http4s.server.handlers.BookHandlersImpl
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

import io.circe.generic.auto.*
import io.circe.syntax.*

object BookRoutes {

  def bookRoutes[F[_] : Monad]: HttpRoutes[F] = {
    val dsl = Http4sDsl[F]
    import dsl.*

    HttpRoutes.of[F] {
      case GET -> Root / "web" / "books" =>
        BookHandlersImpl.getBooks() match
          case Some(books) => Ok("hola")
          case _ => NotFound(s"No books fetched.")
    }
    }
}
