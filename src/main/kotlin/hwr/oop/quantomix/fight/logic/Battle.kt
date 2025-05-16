package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import hwr.oop.quantomix.stats.GameData

class Battle(private var listOfQuantomix: MutableList<Quantomix>) {

    internal fun nextAttacker(): MutableList<Quantomix> {
        // sorts the Quantomix according to the speed. At the end of this function is the
        // fasts Quantomix the first in the list.
        if (listOfQuantomix.size < 2) {
            error("Not enough number of players")
        }
        return listOfQuantomix.sortedByDescending { it.battleStats.stats.speed }.toMutableList()
    }

    private fun formulaAttackForce(attackDamage: Int, attackValue: Int, defense: Int, multiFactor: Double): Int {
        val damage = (attackDamage * attackValue * multiFactor) / ((defense / 100 + 1) * 100)
        return damage.toInt()
    }

    private fun buffsAndDebuffs(attack: Attack, quantomixWithStatsToChange: Quantomix) {
        if (attack.buff != null) {
            if (attack.changeStats != null) {
                quantomixWithStatsToChange.battleStats.stats.BuffsDebuffs(attack.changeStats, attack.buff)
            }
        }
    }

    private fun attackPower(damageDealer: Quantomix, effectiv1: Double? = null, effectiv2: Double? = null): Int {
        val attack = damageDealer.battleStats.nextAttack
        val nextAttacker = damageDealer
        when (requireNotNull(attack).type.name == nextAttacker.typ1.name || (nextAttacker.typ2 != null && attack.type.name == nextAttacker.typ2.name)) {
            true -> return formulaAttackForce(
                requireNotNull(attack.damage),
                nextAttacker.battleStats.stats.specialAttack,
                requireNotNull(nextAttacker.battleStats.target).battleStats.stats.specialDefense,
                formulaEffectivity(damageDealer, effectiv1, effectiv2)
            )

            false -> return formulaAttackForce(
                requireNotNull(attack.damage),
                nextAttacker.stats.attack,
                requireNotNull(nextAttacker.battleStats.target).battleStats.stats.defense,
                effectivity(
                    requireNotNull(damageDealer.battleStats.target).typ1,
                    requireNotNull(damageDealer.battleStats.nextAttack).type,
                    formulaEffectivity(damageDealer, effectiv1, effectiv2)
                )
            )
        }
    }

    private fun formulaEffectivity(damageDealer: Quantomix, effectiv1: Double?, effectiv2: Double?): Double {
        val target = damageDealer.battleStats.target
        val typ2 =
            requireNotNull(target).typ2 ?: Typ("0") // Setzt "0" als Standard, falls kein zweiter Typ vorhanden ist

        val eff1 = effectivity(target.typ1, requireNotNull(damageDealer.battleStats.nextAttack).type, effectiv1)
        val eff2 = effectivity(typ2, requireNotNull(damageDealer.battleStats.nextAttack).type, effectiv2)

        return when {
            eff1 * eff2 == 1.0 -> 1.0
            eff1 >= eff2 -> eff1
            else -> eff2
        }
    }

    private fun effectivity(quantomixType: Typ, attackTyp: Typ, effective: Double? = null): Double {
        if (effective != null) {
            return effective
        } else {
            if (quantomixType.name == "0") {
                return 1.0
            }
            return GameData.getEffektivitaet(quantomixType.name, attackTyp.name)
        }
    }

    fun start(effectiv: List<Double>? = null): List<Quantomix> {
        val attackOrder = nextAttacker()
        var indexEffectivityList = effectiv?.let { 0 }

        val iterator = attackOrder.iterator()
        while (iterator.hasNext()) {
            val attacker = iterator.next()
            val target = attacker.battleStats.target ?: continue

            val power = if (effectiv != null) {
                attackPower(
                    attacker,
                    effectiv[requireNotNull(indexEffectivityList)],
                    effectiv[indexEffectivityList + 1]
                )
            } else {
                attackPower(attacker)
            }
            if (power >= target.battleStats.stats.kp) {
                target.battleStats.newKp(power)
                attackOrder.remove(target)
            } else {
                target.battleStats.newKp(power)
                if (requireNotNull(attacker.battleStats.nextAttack).buff == true) {
                    buffsAndDebuffs(requireNotNull(attacker.battleStats.nextAttack), attacker)
                } else {
                    buffsAndDebuffs(requireNotNull(attacker.battleStats.nextAttack), target)
                }
            }
            indexEffectivityList?.let { indexEffectivityList++ }
        }
        return attackOrder
    }
}
