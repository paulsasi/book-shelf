package net.paulsasi.bs.entities

import io.circe.{Encoder, Json}
import io.circe.syntax._

object Topics extends Enumeration {
  type Topic = Value
  val SCI_FI,
  HORROR,
  ADVENTURE
  = Value
}

implicit val topicEncoder: Encoder[Topics.Topic] = (t: Topics.Topic) => t.toString.asJson
