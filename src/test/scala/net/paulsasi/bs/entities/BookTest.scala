import org.scalatest.funsuite.AnyFunSuite

import net.paulsasi.bs.entities.Book
import net.paulsasi.bs.entities.Author
import net.paulsasi.bs.entities.Topics
import net.paulsasi.bs.utils.Date

import java.time.LocalDate

class BookTest extends AnyFunSuite {
  test("Book.constructor") {
    val id = 1
    val name = "Book Name"
    val author = Author(1, "Name", "Surname", "Spain")
    val release = Date(LocalDate.of(2021, 10, 10))
    val topic = Topics.HORROR
    val book = Book(id, name, author, release, topic)
    assert(
              book.id == 1 &&
              book.name == name &&
              book.author == author &&
              book.releaseDate == release &&
              book.topic == topic
    )
  }
}
