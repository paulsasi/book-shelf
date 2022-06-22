package net.paulsasi.bs.entities

import io.circe.Encoder
import io.circe.Json
import io.circe.syntax._

import net.paulsasi.bs.utils.Date
import net.paulsasi.bs.utils.dateEncoder
import net.paulsasi.bs.entities.Topics
import net.paulsasi.bs.entities.topicEncoder

case class Book(id: Long, name: String, author: Author, releaseDate: Date, topic: Topics.Topic) {}

implicit val bookEncoder: Encoder[Book] = {
  (b: Book) => Json.obj(
                          ("id", b.id.asJson),
                          ("name", b.name.asJson),
                          ("author", b.author.asJson),
                          ("date", b.releaseDate.asJson),
                          ("topic", b.topic.asJson)
  )
}

