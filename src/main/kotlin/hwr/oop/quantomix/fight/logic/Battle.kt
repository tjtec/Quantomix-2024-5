package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats

interface Battle {
    fun simpleBattle(
        aktiveQuantomixBattleStats: BattleStats,
        attack: Attack,
        target: BattleStats,
        attackStrategy: DamageStrategy,
        ):Boolean
}

class SimpleBattle: Battle {
    // muss in Rounds ausgelagert werden
    /*
    internal fun nextAttacker(): MutableList<Quantomix> {
        // sorts the Quantomix according to the speed. At the end of this function is the
        // fasts Quantomix the first in the list.
        if (listOfQuantomix.size < 2) {
            error("Not enough number of players")
        }
        return listOfQuantomix.sortedByDescending { it.getStats().getSpeed() }.toMutableList()
    }
     */

    /*
    private fun attack(
        attacker: Quantomix,
        target: Quantomix,
        attackOrder: MutableList<Quantomix>,
        effectiv: List<Float>?,
        indexEffectivityList: Int?
    ) {
        val power = if (effectiv != null) {
            attackPower(
                attacker,
                effectiv[requireNotNull(indexEffectivityList)],
                effectiv[indexEffectivityList + 1]
            )
        } else {
            attackPower(attacker)
        }
        requireNotNull(attacker.battleStats.nextAttack).changeStats(attacker.battleStats)
        if (!target.battleStats.status.noDamage) {
            if (power >= target.battleStats.stats.kp) {
                target.battleStats.newKp(power)
                attackOrder.remove(target)
            } else if (attacker.battleStats.stats.kp == 0) {
                attackOrder.remove(attacker)
            } else {
                target.battleStats.newKp(power)
            }
        }
    }

    fun start(): BattleStats {
        val attackOrder = nextAttacker()
        var indexEffectivityList = effectiv?.let { 0 }

        var indexForAttackOrder = 0
        while (attackOrder.size > indexForAttackOrder) {
            val attacker = attackOrder[indexForAttackOrder]
            val target = attacker.battleStats.target ?: continue
            attack(attacker, target, attackOrder, effectiv, indexEffectivityList)
            indexForAttackOrder += 1
            indexEffectivityList?.let { indexEffectivityList++ }
        }
        return attackOrder
    }
     */
    //ToDo: Wo muss die Verbindung von den BattleStats hin? BattleStats sind mit Quantomix verbunden,
    // aber von Quantomix aus gibt es keine Verbindung dahin muss das so, oder ist das anders Lösbar?

    override fun simpleBattle (aktiveQuantomixBattleStats: BattleStats, attack: Attack, target: BattleStats, strategy: DamageStrategy):Boolean {
        if (hits(attack)) {
            val damage=strategy.damageFunction(aktiveQuantomixBattleStats, target, attack)
            target.getStats().newKp(damage)
        }
        return hits(attack)
    }

    private fun hits(attack: Attack): Boolean {
        val randomValue = (1..100).random()
        return when (randomValue <= attack.getDamageQuote()) {
            true -> true
            else -> false
        }
    }
}