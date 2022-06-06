import org.scalatest.funsuite.AnyFunSuite

import net.paulsasi.bs.utils.Date
import java.time.LocalDate

class DateTest extends AnyFunSuite {
  test("Date.constructor") {
    val input = LocalDate.of(2022, 10, 25)
    val date = Date(input)
    assert(date.value == input)
  }

}
