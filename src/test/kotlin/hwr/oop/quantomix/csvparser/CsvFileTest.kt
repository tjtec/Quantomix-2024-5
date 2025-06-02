package hwr.oop.quantomix.csvparser

import io.kotest.core.spec.style.AnnotationSpec
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.File

class CsvFileTest : AnnotationSpec() {

  @Test
  fun `CSV file with 2 lines should be parsed correctly`() {
    val testCsvFile = "src/test/resources/test.csv"
    val csvFile = CsvFile.parseCsv(File(testCsvFile))
    assertEquals(2, csvFile.lines.size)
  }
}