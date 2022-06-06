package net.paulsasi.bs.entities

import net.paulsasi.bs.utils.Date

class Book(idc: Long, namec: String, authorc: Author, releaseDatec: Date, topicc: Topics.Topic) {

  val id: Long = idc
  val name: String = namec
  val author: Author = authorc
  val releaseDate: Date = releaseDatec
  val topic: Topics.Topic = topicc

}
