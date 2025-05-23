package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach


class Rounds(private val trainer1: Coach, private val trainer2: Coach) {
    private val chosenAttacksMap = mutableMapOf<Quantomix, Attack>()
    private val damageFunction: DamageStrategy = StandardDamageStrategy()
    private val battle:Battle=SimpleBattle()

    private val quantomixAndBattleStatsMap= mutableMapOf<Quantomix, BattleStats>()


    private val activeQuantomixTrainer1 = trainer1.getFirstQuantomix()
    private val activeQuantomixTrainer2 = trainer2.getFirstQuantomix()

    fun choseAttack(
        attackingTrainer: Coach,
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
        val quantomixInOrderOfAttack = quantomixsBySpeed()

        for (attacker in quantomixInOrderOfAttack) {
            val defender = otherQuantomix(attacker)

            val battleStats = getBattleStatsForCombatants(attacker, defender)

            battle.simpleBattle(
                battleStats.first,
                chosenAttacksMap.getValue(attacker),
                battleStats.second,
                damageFunction
            )
        }
        chosenAttacksMap.clear()
    }

    private fun getBattleStatsForCombatants(attacker: Quantomix, defender: Quantomix): Pair<BattleStats, BattleStats> {
        val attackerStats = quantomixAndBattleStatsMap.getOrPut(attacker) { attacker.newBattleStats() }
        val defenderStats = quantomixAndBattleStatsMap.getOrPut(defender) { defender.newBattleStats() }

        return Pair(attackerStats, defenderStats)
    }

    private fun quantomixsBySpeed(): List<Quantomix> {
        return listOf(activeQuantomixTrainer1, activeQuantomixTrainer2)
            .sortedByDescending { it.getStats().getSpeed() }
    }

        private fun otherQuantomix(quantomix: Quantomix) =
            if (quantomix == activeQuantomixTrainer1) activeQuantomixTrainer2 else activeQuantomixTrainer1

//        private fun defendingQuantomix(attacking: Coach) =
//            if (attacking == trainer2) activeQuantomixTrainer2 else activeQuantomixTrainer1
}