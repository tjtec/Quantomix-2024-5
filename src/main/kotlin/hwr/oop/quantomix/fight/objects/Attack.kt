package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.fight.logic.BattleStats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ

data class Attack(
    val attackName: String,
    val type: Typ,
    val damage: Int,
    val damageQuote: Int,
    val effects: MutableList<Effects>,
    private val status: Status? = null
)

class BattleAttack(attack: Attack, target: Quantomix) {
    fun changeStats(battleStatsAttacker: BattleStats) {
        var alreadyChangedEffects = 0
        while (!(effects.isEmpty()) && requireNotNull(effects).size > alreadyChangedEffects) {
            requireNotNull(effects)[alreadyChangedEffects].buffsAndDebuffs()
            alreadyChangedEffects += 1
        }
        if (status != null) {
            battleStatsAttacker.getTarget()!!.battleStats.changeStatus(status)
        }
    }
}
