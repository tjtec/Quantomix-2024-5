package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

//ToDo: mehr als ein Quantomix implementieren^Doppelkampf/Dreifachkampf
//ToDo: Exeption schreiben Attacke ist nicht in der Liste möglicher auswählbarer Attacken (vielleicht auch erst ins CLI)

interface Rounds {
  fun choseAttack(
    attackingTrainer: Quantomix,
    attack: Attack,
  )
}

class RoundsWithOneQuantomix(private var trainer1: Coach, private var trainer2: Coach): Rounds {
  private val chosenAttacksMap = mutableMapOf<Quantomix, Attack>()
  private val damageFunction: DamageStrategy = StandardDamageStrategy()
  private val battle = SimpleBattle()
  //private val saveMethode: Save = CSVSave()
  //private val loadMethode: Load = CSVLoad()

  private var quantomixAndBattleStatsMap =
    mutableMapOf<Quantomix, BattleStats>()


  private val activeQuantomixTrainer1 = trainer1.getFirstQuantomix()
  private val activeQuantomixTrainer2 = trainer2.getFirstQuantomix()

  override fun choseAttack(
    attackingTrainer: Quantomix,
    attack: Attack,
  ) {
    val attackingQuantomix = attackingQuantomix(attackingTrainer)
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


  private fun attackingQuantomix(attackingTrainer: Coach) =
    if (attackingTrainer == trainer1) activeQuantomixTrainer1 else activeQuantomixTrainer2


  private fun simulateBattle() {
    val battleStatsInOrderOfAttack = quantomixBySpeed()
    val quantomixInOrderOfAttack = mutableListOf<Quantomix>()
    for (quantomix in battleStatsInOrderOfAttack.keys) {
      quantomixInOrderOfAttack.add(quantomix)
    }
    for (attacker in quantomixInOrderOfAttack) {
      val defender = otherQuantomix(attacker)

      val battleStats = getBattleStatsForCombatants(attacker, defender)
      if (battleStats.first.isAlive()) {
        battle.simpleBattle(
          aktiveQuantomixBattleStats = battleStats.first,
          attack = chosenAttacksMap.getValue(attacker),
          target = battleStats.second,
          attackStrategy = damageFunction
        )
        //saveMethode.save(trainer1, trainer2, quantomixAndBattleStatsMap)
      }
    }
    chosenAttacksMap.clear()
  }

  private fun getBattleStatsForCombatants(
    attacker: Quantomix,
    defender: Quantomix,
  ): Pair<BattleStats, BattleStats> {
    val attackerStats =
      quantomixAndBattleStatsMap.getOrPut(attacker) { attacker.newBattleStats() }
    val defenderStats =
      quantomixAndBattleStatsMap.getOrPut(defender) { defender.newBattleStats() }

    return Pair(attackerStats, defenderStats)
  }

  private fun quantomixBySpeed(): Map<Quantomix, BattleStats> {
    val activeQuantomix1 =
      quantomixAndBattleStatsMap.getOrPut(activeQuantomixTrainer1) {
        activeQuantomixTrainer1.newBattleStats()
      }
    val activeQuantomix2 =
      quantomixAndBattleStatsMap.getOrPut(activeQuantomixTrainer2) {
        activeQuantomixTrainer2.newBattleStats()
      }
    val listOfBattleStats =
      listOf(
        activeQuantomix1, activeQuantomix2
      ).sortedByDescending { it.getStats().getSpeed() }
    val mapQuantomixAndBattleStats = mutableMapOf<Quantomix, BattleStats>()
    var indexCurrentQuantomix = 0
    while (listOfBattleStats.size != mapQuantomixAndBattleStats.size) {
      mapQuantomixAndBattleStats.put(
        listOfBattleStats[indexCurrentQuantomix].getQuantomix(),
        listOfBattleStats[indexCurrentQuantomix]
      )
      indexCurrentQuantomix++
    }
    return mapQuantomixAndBattleStats.toMap()
  }

  private fun otherQuantomix(quantomix: Quantomix) =
    if (quantomix == activeQuantomixTrainer1) activeQuantomixTrainer2 else activeQuantomixTrainer1

  /*private fun loadRound() {
    val loadHelper = loadMethode.getHelper()
    quantomixAndBattleStatsMap = loadHelper.quantomixAndBattleStatsMap
    trainer1 = loadHelper.trainer1
    trainer2 = loadHelper.trainer2
  }
   */
}