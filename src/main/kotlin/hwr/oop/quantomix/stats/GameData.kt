package hwr.oop.quantomix.stats

import hwr.oop.quantomix.fight.objects.Effektivitaet
import hwr.oop.quantomix.csvparser.CsvFile
import java.io.File

class GameData {
    //Global Game Data
    val monsterDB = File("src/main/resources/quantomix.csv");
    val effDB = File("src/main/resources/Effektivitaet.csv");

    public fun getEffektivitaet(quantomixType: String, attackType: String): Double {
        val csvFile = CsvFile.parseCsv(effDB)
        val row = Effektivitaet().Klassen.get(attackType);
        val col = Effektivitaet().Klassen.get(quantomixType)
        val effektivitaet = csvFile.lines[row!!].values[col!!];
        return Effektivitaet().scale[effektivitaet]!!;
    }
}

fun main(){
    val returnval = GameData().getEffektivitaet("Normal", "Kampf");
    println(returnval);
}