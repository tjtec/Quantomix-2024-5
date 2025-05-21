package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats

class Damage(private val battleStats: BattleStats, private val attack: Attack, battleStatsTarget: BattleStats) {
    init {
        val effectivity = effectivity()
        if (attack.getSpecialAttack()) {
            formulaAttackForce(
                attackDamage = attack.getDamage(),
                attackValue = battleStats.getStats().getSpecialAttack(),
                defense = battleStats.getStats().getSpecialDefense(),
                multiFactor = effectivity,
            )
        } else {
            formulaAttackForce(
                attackDamage = attack.getDamage(),
                attackValue = battleStats.getStats().getAttack(),
                defense = battleStatsTarget.getStats().getDefense(),
                multiFactor = effectivity
            )

        }
        //ToDo: Sollen für eine Attacke nur Buff oder Debuff möglich sein? Wie verhält es sich mit den
        // Buffs und Debuffs, sind Debuffs immer auf das Ziel der Attacke bezogen und Buffs immer auf
        // den Angreifer?
        //ToDo:Einfügen der Buff und Debuff Möglichkeit
    }

    private fun formulaAttackForce(attackDamage: Int, attackValue: Int, defense: Int, multiFactor: Float): Int {
        val damage = (attackDamage * attackValue * multiFactor) / ((defense / 100 + 1) * 100)
        return damage.toInt()
    }

    //Float oder Double? (Vielleicht gute Frage an die Dozenten
    private fun effectivity(): Float {
        val effectivity1 = battleStats.getQuantomix().getType1().getEffectivity(attack)
        val type2 = battleStats.getQuantomix().getType2()
        val effectivity2 = when (type2 != null) {
            true -> type2.getEffectivity(attack)
            false -> null
        }
        return when (effectivity2 == null) {
            true -> effectivity1
            false -> when (effectivity1 >= effectivity2) {
                true -> effectivity1
                false -> effectivity2
            }
        }
    }
}