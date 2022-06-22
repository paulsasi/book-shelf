package net.paulsasi.bs.entities

import io.circe.Encoder
import io.circe.Json
import io.circe.syntax._

case class Author (id: Long, name: String, surname: String, nationality: String){
}

implicit val authorEncoder: Encoder[Author] = {
  (a: Author) => Json.obj(
                            ("name", a.name.asJson),
                            ("surname", a.surname.asJson),
                            ("nationality", a.nationality.asJson)
  )
}
