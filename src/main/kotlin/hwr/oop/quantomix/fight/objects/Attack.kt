package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.fight.logic.BattleStats
import hwr.oop.quantomix.objects.Typ

class Attack(
    val attackName: String,
    val type: Typ,
    val damage: Int,
    var damageQuote: Int,
    var effects: MutableList<Effects>,
    private val status: Status? = null
) {
    fun changeStats(battleStatsAttacker: BattleStats) {
        var alreadyChangedEffects = 0
        while (!(effects.isEmpty()) && requireNotNull(effects).size > alreadyChangedEffects) {
            requireNotNull(effects)[alreadyChangedEffects].buffsAndDebuffs()
            alreadyChangedEffects += 1
        }
        if (status != null) {
            battleStatsAttacker.getTarget()!!.battleStats.changeStatus()
        }
    }
}
