package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats

interface Battle {
    fun simpleBattle(
        aktiveQuantomixBattleStats: BattleStats,
        attack: Attack,
        target: BattleStats,
        attackStrategy: DamageStrategy,
    ): Boolean
}

class SimpleBattle : Battle {
    override fun simpleBattle(
        aktiveQuantomixBattleStats: BattleStats,
        attack: Attack,
        target: BattleStats,
        attackStrategy: DamageStrategy
    ): Boolean {
        if (attack.hits()) {
            val damage = attackStrategy.damageFunction(aktiveQuantomixBattleStats, target, attack)
            target.newKp(damage)
            attack.changeStats(aktiveQuantomixBattleStats, target)
        }
        return target.isAlive()
    }
}