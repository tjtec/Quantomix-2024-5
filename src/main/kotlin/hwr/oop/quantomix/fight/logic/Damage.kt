package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.fight.objects.Status
import hwr.oop.quantomix.monster.Quantomix

interface DamageStrategy {
    fun damageFunction(
        battleStats1: BattleStats,
        battleStats2: BattleStats,
        attack: Attack,
    ): Int
}

class Damage: DamageStrategy {
    private var battleStats : BattleStats? = null
    private var attack : Attack? = null
    private var battleStatsTarget: BattleStats? = null
    override fun damageFunction(battleStats1: BattleStats, attack1: Attack, battleStatsTarget1: BattleStats) : Int {
        val effectivity = effectivity()
        battleStats = battleStats1
        attack = attack1
        battleStatsTarget = battleStatsTarget1
        if (attack!!.getSpecialAttack() ) {
            formulaAttackForce(
                attackDamage = attack!!.getDamage(),
                attackValue = battleStats!!.getStats().getSpecialAttack(),
                defense = battleStats!!.getStats().getSpecialDefense(),
                multiFactor = effectivity,
            )
        } else {
            formulaAttackForce(
                attackDamage = attack!!.getDamage(),
                attackValue = battleStats!!.getStats().getAttack(),
                defense = battleStatsTarget!!.getStats().getDefense(),
                multiFactor = effectivity
            )

        }
        //ToDo: Sollen für eine Attacke nur Buff oder Debuff möglich sein? Wie verhält es sich mit den
        // Buffs und Debuffs, sind Debuffs immer auf das Ziel der Attacke bezogen und Buffs immer auf
        // den Angreifer?
        //ToDo:Einfügen der Buff und Debuff Möglichkeit
    }

    private fun statusEffect(): Int {
        val status = attack!!.getStatus()
        return if (status != null) {
            when (status) {
                Status.NoDamage -> 0
                Status.Poison -> 1 / 16
                Status.StrongPoison -> 1 / 16 //ToDo:Pro Runde multiplizierender Schaden
                Status.Combustion -> 1 / 8
                Status.Sleep -> 0
                Status.Freeze -> 0
                else -> 1
            }
        } else {
            1
        }
    }


    private fun formulaAttackForce(attackDamage: Int, attackValue: Int, defense: Int, multiFactor: Float): Int {
        val damage = (attackDamage * attackValue * multiFactor) / ((defense / 100 + 1) * 100)
        return damage.toInt()
    }

    //TODO: Float oder Double? (Vielleicht gute Frage an die Dozenten
    private fun effectivity(): Float {
        val effectivity1 = battleStats!!.getQuantomix().getType1().getEffectivity(attack!!)
        val type2 = battleStats!!.getQuantomix().getType2()
        val effectivity2 = when (type2 != null) {
            true -> type2.getEffectivity(attack!!)
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