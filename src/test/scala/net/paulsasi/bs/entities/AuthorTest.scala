import org.scalatest.funsuite.AnyFunSuite

import net.paulsasi.bs.entities.Author

class AuthorTest extends AnyFunSuite {
  test("Author.constructor") {
    val author = Author(1, "Paul", "Sasieta", "Spain")
    assert(
            author.id == 1 &&
            author.name == "Paul" &&
            author.surname == "Sasieta" &&
            author.nationality == "Spain"
    )
  }
}
