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
    override fun simpleBattle(
        aktiveQuantomixBattleStats: BattleStats,
        attack: Attack,
        target: BattleStats,
        attackStrategy: DamageStrategy
    ): Boolean {
        if (hits(attack)) {
            val damage = attackStrategy.damageFunction(aktiveQuantomixBattleStats, target, attack)
            target.getStats().newKp(damage)
            attack.changeStats(aktiveQuantomixBattleStats, target)
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