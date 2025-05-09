package hwr.oop.quantomix.stats

import com.sun.tools.javac.tree.TreeInfo.types
import hwr.oop.quantomix.csvparser.CsvFile
import hwr.oop.quantomix.csvparser.CsvLine
import hwr.oop.quantomix.fight.logic.BattleStats
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Effektivitaet
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import java.io.File

object GameData {
    //Global Game Data
    val effDB = File("src/main/resources/Effektivitaet.csv")
    val qStore = File("./qstore.csv")

    fun getEffektivitaet(quantomixType: String, attackType: String): Double {
        val csvFile = CsvFile.parseCsv(effDB)
        val row = Effektivitaet().Klassen.get(attackType) ?: throw IllegalArgumentException("Angriffstyp '$attackType' nicht gefunden.")
        val col = Effektivitaet().Klassen.get(quantomixType) ?: throw IllegalArgumentException("Quantomix-Typ '$quantomixType' nicht gefunden.")
        val effektivitaet = csvFile.lines.getOrNull(row)?.values?.getOrNull(col) ?: throw IllegalStateException("Effektivitätswert für '$attackType' gegen '$quantomixType' nicht gefunden.")
        return Effektivitaet().scale[effektivitaet] ?: throw IllegalStateException("Skalierungswert für '$effektivitaet' nicht gefunden.")
    }

    fun storeQuantomix(quantomix: Quantomix): String{
        val name = quantomix.quantomixName
        var typ1 = ""
        var typ2 = ""
        var kp = quantomix.kp
        val attack = quantomix.attack
        val defense = quantomix.defense
        val specialAttack = quantomix.specialAttack
        val specialDefense = quantomix.specialDefense
        val speed = quantomix.speed
        var attacks = ""
        var battleStats = quantomix.battleStats
        val s = CsvFile.seperator
        val si = "|"
        var count = 0
        for (i in quantomix.attacks) {
            attacks = "${attacks}<EOA>${i.attackName}${si}${i.damage}${si}${i.damageQuote}${si}${i.type.name}"
            if(count == 0) {
                attacks = attacks.replace("<EOA>", "")
            }
            count = count+1
        }
        fun typetostring(type: Typ): String {
            return "${type.name}"
        }
        typ1 = typetostring(quantomix.typ1)
        typ2 = typetostring(quantomix.typ2!!)
        val line = "${name}${s}${typ1}${s}${typ2}${s}${kp}${s}${attack}${s}${defense}${s}${specialAttack}${s}${specialDefense}${s}${speed}${s}${attacks}${s}${battleStats}\n"
        qStore.appendText(line)
        return line
    }
}