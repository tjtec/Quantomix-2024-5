package hwr.oop.quantomix.objects

import hwr.oop.quantomix.monster.Quantomix

class Coach(
  val coachName: String,
  val quantomixTeam: List<Quantomix>,
) {
  init {
    require(quantomixTeam.isNotEmpty()) { "Ein Coach muss mindestens einen Quantomix besitzen." }
    require(quantomixTeam.size <= 6) { "Ein Coach darf höchstens 6 Quantomix besitzen, aber es wurden ${quantomixTeam.size} übergeben." }
  }

  fun getFirstQuantomix(): Quantomix {
    return quantomixTeam[0]
  }

  fun getActiveQuantomixes(numberOfQuantomixInRound: Int): List<Quantomix> {
    require(numberOfQuantomixInRound > 0) { "Die Anzahl der Quantomix muss positiv sein." }
    require(numberOfQuantomixInRound <= quantomixTeam.size) { "Nicht genügend Quantomix im Team" }
    return quantomixTeam.take(numberOfQuantomixInRound)
  }

}