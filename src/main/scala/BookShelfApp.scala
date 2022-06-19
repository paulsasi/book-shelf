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
import net.paulsasi.bs.boot.Boot


object BookShelfApp extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    
    Boot

    val apis = Router(
      "/api" -> authorRoutes[IO]
    ).orNotFound

    BlazeServerBuilder[IO](runtime.compute)
      .bindHttp(8080, "localhost")
      .withHttpApp(apis)
      .resource
      .use(_ => IO.never)
      .as(ExitCode.Success)
  }
}


//  object IdQueryParamMatcher extends QueryParamDecoderMatcher[Float]("id")
//
//  val authorDB: mutable.Map[Int, Author] =
//    mutable.Map(
//      1 -> Author(1, "Paul", "Sasieta", "Spain")
//  )
//
//  // GET /api/web/author?id=10
//  def authorRoutes[F[_]: Monad]: HttpRoutes[F] = {
//    val dsl = Http4sDsl[F]
//    import dsl._
//
//    HttpRoutes.of[F] {
//      case GET -> Root / "api" / "web" / "author" :? IdQueryParamMatcher(id) =>
//        authorDB.get(id.toInt) match {
//          case Some(author) => Ok(author.name)
//          case _ => NotFound(s"No author with id $id found.")
//        }
//    }
//  }
//
//  override def run(args: List[String]): IO[ExitCode] = {
//    val apis = Router(
//      "/api" -> authorRoutes[IO]
//    ).orNotFound
//
//    BlazeServerBuilder[IO](runtime.compute)
//      .bindHttp(8080, "localhost")
//      .withHttpApp(apis)
//      .resource
//      .use(_ => IO.never)
//      .as(ExitCode.Success)
//  }

