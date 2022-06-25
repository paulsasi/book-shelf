package net.paulsasi.bs.utils

import io.circe.Encoder
import io.circe.Json
import io.circe.syntax._

import net.paulsasi.bs.entities.Book

import java.time.LocalDate


case class Date(date: LocalDate) {}

def dateFromString(s: String): Date = return Date(LocalDate.parse(s))

implicit val dateEncoder: Encoder[Date] = (d: Date) => d.date.toString.asJson


