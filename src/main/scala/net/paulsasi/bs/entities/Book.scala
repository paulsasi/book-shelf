package net.paulsasi.bs.entities

import net.paulsasi.bs.utils.Date
import net.paulsasi.bs.entities.Topics

case class Book(id: Long, name: String, author: Author, releaseDate: Date, topic: Topics.Topic) {}
