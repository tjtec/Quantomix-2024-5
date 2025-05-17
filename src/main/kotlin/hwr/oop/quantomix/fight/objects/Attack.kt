package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.fight.logic.BattleStats
import hwr.oop.quantomix.objects.Typ

class Attack(
    val attackName: String,
    val type: Typ,
    val damage: Int,
    val damageQuote: Int,
    var effects: MutableList<Effects>? = null,
    var noDamage: Boolean = false,
) {
    fun changeStats(battleStatsAttacker: BattleStats) {
        var alreadyChangedEffects = 0
        while (!(effects.isNullOrEmpty()) && requireNotNull(effects).size > alreadyChangedEffects) {
            requireNotNull(effects)[alreadyChangedEffects].buffsAndDebuffs()
            alreadyChangedEffects += 1
        }
        if (noDamage) {
            battleStatsAttacker.noDamage = true
        }
    }
}