package hwr.oop.quantomix.objects

import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.monster.Quantomix
import kotlinx.serialization.Serializable

@Serializable
class Coach(
  val coachName: String,
  val quantomixTeam: List<Quantomix>,
) {
  init {
    require(quantomixTeam.isNotEmpty()) {
      "Ein Coach muss mindestens einen " +
          "Quantomix besitzen."
    }
    require(quantomixTeam.size <= 6) {
      "Ein Coach darf höchstens 6 Quantomix " +
          "besitzen, aber es wurden ${quantomixTeam.size} übergeben."
    }
  }

  fun getActiveQuantomixes(
    numberOfQuantomixInRound: Int,
    battleStatsMap: Map<Quantomix, BattleStats>? = null,
  ): List<Quantomix> {
    require(numberOfQuantomixInRound > 0) {
      "Die Anzahl der Quantomix " +
          "muss positiv sein."
    }
    require(numberOfQuantomixInRound <= quantomixTeam.size) {
      "Nicht genügend " +
          "Quantomix im Team"
    }

    val activeTeam = quantomixTeam.filter { quantomix ->
      battleStatsMap?.get(quantomix)?.isAlive() ?: true
    }
    return activeTeam.take(numberOfQuantomixInRound)
  }
}