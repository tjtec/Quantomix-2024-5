package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class Battle(
  private val trainer1: Coach,
  trainer2: Coach,
  numberOfQuantomixInRound: Int = 1,
  damageStrategy: DamageStrategy = StandardDamageStrategy()

) {
  private data class ChosenAction(val attack: Attack, val target: Quantomix)
  private val chosenActionsMap = mutableMapOf<Quantomix, ChosenAction>()

  private val damageFunction: DamageStrategy = damageStrategy
  private val battle = Round()
  private val quantomixAndBattleStatsMap = mutableMapOf<Quantomix, BattleStats>()

  private val activeQuantomixesTrainer1: List<Quantomix> = trainer1.getActiveQuantomixes(numberOfQuantomixInRound)
  private val activeQuantomixesTrainer2: List<Quantomix> = trainer2.getActiveQuantomixes(numberOfQuantomixInRound)

  /**
   * Diese Methode wird vom CLI aufgerufen, wenn ein Trainer seinen Angriff sowie das Ziel auswählt.
   * Dabei wird intern zunächst der angreifende Quantomix (der noch keine Aktion gewählt hat und am Leben ist)
   * ermittelt, und anschließend wird geprüft, ob der gewählte Angriff vorhanden ist und das Ziel
   * (vom Gegnerteam) gültig ist.
   */
  fun chooseAttack(attackingTrainer: Coach, attack: Attack, target: Quantomix) {
    // Bestimme den nächsten verfügbaren Quantomix, der angreifen kann.
    val attacker = getNextAttackingQuantomix(attackingTrainer)

    require(attacker.hasAttack(attack)) {
      "Attacking Quantomix does not have the attack"
    }
    // Stelle sicher, dass das Ziel zu den aktiven Einheiten des gegnerischen Trainers gehört.
    val enemyTeam = if (attackingTrainer == trainer1) activeQuantomixesTrainer2 else activeQuantomixesTrainer1
    require(enemyTeam.contains(target)) {
      "Das gewählte Ziel gehört nicht zum gegnerischen Team."
    }
    // Speichere die gewählte Aktion für den Angreifer.
    chosenActionsMap[attacker] = ChosenAction(attack, target)

    // Sobald alle aktiven Quantomix (beider Teams) ihren Zug gewählt haben, wird der Kampf simuliert.
    if (chosenActionsMap.size == (activeQuantomixesTrainer1.size + activeQuantomixesTrainer2.size)) {
      simulateBattle()
    }
  }

  // Ermittelt den nächsten aktiven Quantomix des Trainers, der noch keinen Angriff gewählt hat.
  // Voraussetzung: Dieser muss noch am Leben sein.
  private fun getNextAttackingQuantomix(trainer: Coach): Quantomix {
    val activeList = if (trainer == trainer1) activeQuantomixesTrainer1 else activeQuantomixesTrainer2
    return activeList.firstOrNull { quantomix ->
      chosenActionsMap[quantomix] == null && getBattleStats(quantomix).isAlive()
    } ?: throw IllegalStateException("Attacking trainer has already chosen an attack")
  }

  // Liest für einen Quantomix (falls noch nicht vorhanden) dessen Kampfdaten.
  private fun getBattleStats(quantomix: Quantomix): BattleStats =
    quantomixAndBattleStatsMap.getOrPut(quantomix) { quantomix.newBattleStats() }

  // Führt den Kampf aus, nachdem beide Teams ihre Aktionen gewählt haben.
  // Die Aktionen werden in Reihenfolge der Geschwindigkeit (höchste zuerst) abgearbeitet.
  private fun simulateBattle() {
    val allQuantomixes = activeQuantomixesTrainer1 + activeQuantomixesTrainer2

    // Sorge dafür, dass für alle Quantomix Kampfdaten vorhanden sind.
    allQuantomixes.forEach { getBattleStats(it) }

    // Sortiere die Quantomix nach absteigender Geschwindigkeit.
    val quantomixesInOrder = allQuantomixes.sortedByDescending {
      getBattleStats(it).getStats().getSpeed()
    }

    // Abarbeiten der Züge: Für jeden Angreifer wird der vorher über CLI bestimmte Angriff auf das
    // vom Trainer ausgewählte Ziel ausgeführt.
    for (attacker in quantomixesInOrder) {
      val attackerStats = getBattleStats(attacker)
      if (!attackerStats.isAlive()) continue  // Überspringe, falls der Angreifer besiegt wurde.

      val chosenAction = chosenActionsMap[attacker] ?: continue
      val targetStats = getBattleStats(chosenAction.target)

      battle.startAttack(
        aktiveQuantomixBattleStats = attackerStats,
        attack = chosenAction.attack,
        target = targetStats,
        attackStrategy = damageFunction
      )
    }
    // Nach Abschluss der Runde werden die Aktionen zurückgesetzt.
    chosenActionsMap.clear()
  }
}
