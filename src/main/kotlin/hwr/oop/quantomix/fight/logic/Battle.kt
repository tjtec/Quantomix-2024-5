package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class Battle(
  private val trainer1: Coach,
  private val trainer2: Coach,
  private val numberOfQuantomixInRound: Int = 1,
  damageStrategy: DamageStrategy = StandardDamageStrategy(),

  ) {
  private data class ChosenAction(val attack: Attack, val target: Quantomix)

  private val chosenActionsMap = mutableMapOf<Quantomix, ChosenAction>()

  private val damageFunction: DamageStrategy = damageStrategy
  private val round = Round()
  val quantomixAndBattleStatsMap = mutableMapOf<Quantomix, BattleStats>()

  private fun getActiveQuantomixesForTrainer(trainer: Coach): List<Quantomix> {
    return trainer.getActiveQuantomixes(
      numberOfQuantomixInRound,
      battleStatsMap = quantomixAndBattleStatsMap
    )
  }

  fun chooseAttack(attackingTrainer: Coach, attack: Attack, target: Quantomix) {
    val attacker = getNextAttackingQuantomix(attackingTrainer)

    require(attacker.hasAttack(attack)) {
      "Attacking Quantomix does not have the attack"
    }
    val enemyTeam = if (attackingTrainer == trainer1)
      trainer2.getActiveQuantomixes(
        numberOfQuantomixInRound = numberOfQuantomixInRound,
        battleStatsMap = quantomixAndBattleStatsMap
      )
    else
      trainer1.getActiveQuantomixes(
        numberOfQuantomixInRound = numberOfQuantomixInRound,
        battleStatsMap = quantomixAndBattleStatsMap
      )
    require(enemyTeam.contains(target)) {
      "Das gewählte Ziel gehört nicht zum gegnerischen Team."
    }
    chosenActionsMap[attacker] = ChosenAction(attack, target)
    if (chosenActionsMap.size == (getActiveQuantomixesForTrainer(trainer1).size
          + getActiveQuantomixesForTrainer(
        trainer2
      ).size)
    ) {
      simulateBattle()
    }
  }

  private fun getNextAttackingQuantomix(trainer: Coach): Quantomix {
    val activeList =
      if (trainer == trainer1) getActiveQuantomixesForTrainer(trainer1)
      else getActiveQuantomixesForTrainer(
        trainer2
      )
    return activeList.firstOrNull { quantomix ->
      chosenActionsMap[quantomix] == null && getBattleStats(quantomix).isAlive()
    }
      ?: throw IllegalStateException(
        "Attacking trainer has already" +
            " chosen an attack"
      )
  }

  private fun getBattleStats(quantomix: Quantomix): BattleStats =
    quantomixAndBattleStatsMap.getOrPut(quantomix) { quantomix.newBattleStats() }

  private fun simulateBattle() {
    val allQuantomixes =
      getActiveQuantomixesForTrainer(trainer1) + getActiveQuantomixesForTrainer(
        trainer2
      )

    allQuantomixes.forEach { getBattleStats(it) }

    val quantomixesInOrder = allQuantomixes.sortedByDescending {
      getBattleStats(it).getStats().getSpeed()
    }

    for (attacker in quantomixesInOrder) {
      val attackerStats = getBattleStats(attacker)
      if (!attackerStats.isAlive()) continue

      val chosenAction = chosenActionsMap[attacker] ?: continue
      val targetStats = getBattleStats(chosenAction.target)

      round.startAttack(
        aktiveQuantomixBattleStats = attackerStats,
        attack = chosenAction.attack,
        target = targetStats,
        attackStrategy = damageFunction
      )
    }
    chosenActionsMap.clear()
  }
}
