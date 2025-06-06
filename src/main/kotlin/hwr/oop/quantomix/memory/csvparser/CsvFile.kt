package hwr.oop.quantomix.csvparser

import java.io.File

class CsvFile(val lines: List<CsvLine>) {

    companion object {
        fun parseCsv(file: File): CsvFile {
            val seperator = ","
            val lines = mutableListOf<CsvLine>()
            file.forEachLine { line ->
                val values = line.split(seperator)
                val csvLine = CsvLine(values)
                lines.add(csvLine)
            }
            val csvFile = CsvFile(lines)
            return csvFile
        }
    }
}