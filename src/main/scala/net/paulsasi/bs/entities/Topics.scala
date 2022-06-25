package net.paulsasi.bs.entities

import io.circe.{Encoder, Json}
import io.circe.syntax._

enum Topics {
  case SCI_FI
  case HORROR
  case ADVENTURE
}

implicit val topicEncoder: Encoder[Topics] = (t: Topics) => t.toString.asJson
