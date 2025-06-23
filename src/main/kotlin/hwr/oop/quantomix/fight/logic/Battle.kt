package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class Battle(
  private val trainer1: Coach,
  private val trainer2: Coach,
  ) {
  private data class ChosenAction(val attack: Attack, val target: Quantomix)

  private val chosenActionsMap = mutableMapOf<Quantomix, ChosenAction>()
  private val round = Round()
  val quantomixAndBattleStatsMap = mutableMapOf<Quantomix, BattleStats>()

  private fun getActiveQuantomixesForTrainer(trainer: Coach): List<Quantomix> =
    trainer.getActiveQuantomixes(
      BattleFundamentals.numberOfQuantomixInRound,
      battleStatsMap = quantomixAndBattleStatsMap
    )

  private fun getEnemyTeam(attackingTrainer: Coach): List<Quantomix> =
    if (attackingTrainer == trainer1)
      trainer2.getActiveQuantomixes(
        BattleFundamentals.numberOfQuantomixInRound,
        battleStatsMap = quantomixAndBattleStatsMap
      )
    else
      trainer1.getActiveQuantomixes(
        BattleFundamentals.numberOfQuantomixInRound,
        battleStatsMap = quantomixAndBattleStatsMap
      )

  fun chooseAttack(attackingTrainer: Coach, attack: Attack, target: Quantomix) {
    val attacker = getNextAttackingQuantomix(attackingTrainer)
    require(attacker.hasAttack(attack)) { "Attacking Quantomix does not have the attack" }
    val enemyTeam = getEnemyTeam(attackingTrainer)
    require(enemyTeam.contains(target)) { "The chosen target does not belong to the other trainer." }
    chosenActionsMap[attacker] = ChosenAction(attack, target)
    if (chosenActionsMap.size == getActiveQuantomixesForTrainer(trainer1).size + getActiveQuantomixesForTrainer(
        trainer2
      ).size
    ) {
      simulateBattle()
    }
  }

  fun chooseAttacks(
    attackingTrainer: Coach,
    moves: List<Pair<Attack, Quantomix>>,
  ) {
    moves.forEach { (attack, target) ->
      val attacker = getNextAttackingQuantomix(attackingTrainer)
      require(attacker.hasAttack(attack)) { "Attacking Quantomix does not have the attack" }
      val enemyTeam = getEnemyTeam(attackingTrainer)
      require(enemyTeam.contains(target)) { "The chosen target does not belong to the other trainer." }
      chosenActionsMap[attacker] = ChosenAction(attack, target)
    }
    val totalActiveTrainer1 = trainer1.getActiveQuantomixes(
      BattleFundamentals.numberOfQuantomixInRound,
      battleStatsMap = quantomixAndBattleStatsMap
    ).size
    val totalActiveTrainer2 = trainer2.getActiveQuantomixes(
      BattleFundamentals.numberOfQuantomixInRound,
      battleStatsMap = quantomixAndBattleStatsMap
    ).size
    if (chosenActionsMap.size == (totalActiveTrainer1 + totalActiveTrainer2)) {
      simulateBattle()
    }
  }

  private fun getNextAttackingQuantomix(trainer: Coach): Quantomix {
    val activeList =
      if (trainer == trainer1) getActiveQuantomixesForTrainer(trainer1)
      else getActiveQuantomixesForTrainer(trainer2)
    return activeList.firstOrNull { qm ->
      chosenActionsMap[qm] == null && getBattleStats(qm).isAlive()
    }
      ?: throw IllegalStateException("Attacking trainer has already chosen an attack")
  }

  private fun getBattleStats(quantomix: Quantomix): BattleStats =
    quantomixAndBattleStatsMap.getOrPut(quantomix) { quantomix.newBattleStats() }

  private fun simulateBattle() {
    val activeTrainer1 = getActiveQuantomixesForTrainer(trainer1)
    val activeTrainer2 = getActiveQuantomixesForTrainer(trainer2)
    val allQuantomixes = activeTrainer1 + activeTrainer2

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
        attackStrategy = BattleFundamentals.damageStrategy
      )
    }
    chosenActionsMap.clear()
  }
}
