package hwr.oop.quantomix.stats

import hwr.oop.quantomix.csvparser.CsvFile
import hwr.oop.quantomix.fight.objects.Effektivitaet
import java.io.File

object GameData {
    //Global Game Data
    val effDB = File("src/main/resources/Effektivitaet.csv")

    fun getEffektivitaet(quantomixType: String, attackType: String): Float {
        val csvFile = CsvFile.parseCsv(effDB)
        val row = Effektivitaet().Klassen.get(attackType)
            ?: throw IllegalArgumentException("Angriffstyp '$attackType' nicht gefunden.")
        val col = Effektivitaet().Klassen.get(quantomixType)
            ?: throw IllegalArgumentException("Quantomix-Typ '$quantomixType' nicht gefunden.")
        val effektivitaet = csvFile.lines.getOrNull(row)?.values?.getOrNull(col)
            ?: throw IllegalStateException("Effektivitätswert für '$attackType' gegen '$quantomixType' nicht gefunden.")
        val solution = Effektivitaet().scale[effektivitaet]
            ?: throw IllegalStateException("Skalierungswert für '$effektivitaet' nicht gefunden.")
        return solution.toFloat()
    }
}