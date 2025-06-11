package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

//ToDo: mehr als ein Quantomix implementieren^Doppelkampf/Dreifachkampf
//ToDo: Exeption schreiben Attacke ist nicht in der Liste möglicher auswählbarer Attacken (vielleicht auch erst ins CLI)

class Rounds(private var trainer1: Coach, private var trainer2: Coach) {
  private val chosenAttacksMap = mutableMapOf<Quantomix, Attack>()
  private val damageStrategy: DamageStrategy = StandardDamageStrategy()
  private val battleSimulator = SimpleBattle()
  private var battleStatsMap = mutableMapOf<Quantomix, BattleStats>()
  private val activeQuantomixTrainer1: Quantomix = trainer1.getFirstQuantomix()
  private val activeQuantomixTrainer2: Quantomix = trainer2.getFirstQuantomix()

  fun chooseAttack(attackingTrainer: Coach, attack: Attack) {
    val attackingQuantomix = getActiveQuantomix(attackingTrainer)
    require(chosenAttacksMap[attackingQuantomix] == null) {
      "Attacking trainer has already chosen an attack"
    }
    require(attackingQuantomix.hasAttack(attack)) {
      "Attacking Quantomix does not have the attack"
    }
    chosenAttacksMap[attackingQuantomix] = attack
    if (chosenAttacksMap.size == 2) {
      simulateBattle()
    }
  }

  private fun getActiveQuantomix(trainer: Coach): Quantomix =
    if (trainer == trainer1) activeQuantomixTrainer1
    else activeQuantomixTrainer2

  private fun simulateBattle() {
    val battleOrder = getQuantomixSortedBySpeed()
    for (attacker in battleOrder) {
      val defender = getOpponent(attacker)
      val (attackerStats, defenderStats) = getBattleStats(attacker, defender)
      if (attackerStats.isAlive()) {
        battleSimulator.simpleBattle(
          aktiveQuantomixBattleStats = attackerStats,
          attack = chosenAttacksMap.getValue(attacker),
          target = defenderStats,
          attackStrategy = damageStrategy,
        )
      }
    }
    chosenAttacksMap.clear()
  }

  private fun getBattleStats(
    attacker: Quantomix,
    defender: Quantomix,
  ): Pair<BattleStats, BattleStats> {
    val attackerStats =
      battleStatsMap.getOrPut(attacker) { attacker.newBattleStats() }
    val defenderStats =
      battleStatsMap.getOrPut(defender) { defender.newBattleStats() }
    return Pair(attackerStats, defenderStats)
  }

  private fun getQuantomixSortedBySpeed(): List<Quantomix> {
    val stats1 =
      battleStatsMap.getOrPut(activeQuantomixTrainer1)
      { activeQuantomixTrainer1.newBattleStats() }
    val stats2 =
      battleStatsMap.getOrPut(activeQuantomixTrainer2)
      { activeQuantomixTrainer2.newBattleStats() }
    return listOf(stats1, stats2).sortedByDescending {
        it.getStats().getSpeed()
      }.map { it.getQuantomix() }
  }

  private fun getOpponent(quantomix: Quantomix): Quantomix =
    if (quantomix == activeQuantomixTrainer1) activeQuantomixTrainer2
    else activeQuantomixTrainer1
}