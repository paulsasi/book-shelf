//import cats._
//import cats.effect._
//import cats.implicits._
//import org.http4s.circe._
//import org.http4s._
//import io.circe.generic.auto._
//import io.circe.syntax._
//import org.http4s.dsl._
//import org.http4s.dsl.impl._
//import org.http4s.headers._
//import org.http4s.implicits._
//import org.http4s.server._
//import org.http4s.server.blaze.BlazeServerBuilder

import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder

import net.paulsasi.bs.http4s.server.routes.AuthorRoutes.authorRoutes
import net.paulsasi.bs.http4s.server.routes.BookRoutes.bookRoutes
import net.paulsasi.bs.boot.Boot


object BookShelfApp extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {

    Boot

    val apis = Router(
      "/api" -> authorRoutes[IO],
      "/api" -> bookRoutes[IO]
    ).orNotFound

    BlazeServerBuilder[IO](runtime.compute)
      .bindHttp(8080, "localhost")
      .withHttpApp(apis)
      .resource
      .use(_ => IO.never)
      .as(ExitCode.Success)
  }
}
