package hwr.oop.quantomix.fight.logic

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
        return listOfQuantomix.sortedByDescending { it.stats.getSpeed() }.toMutableList()
    }

    private fun formulaAttackForce(attackDamage: Int, attackValue: Int, defense: Int, multiFactor: Float): Int {
        val damage = (attackDamage * attackValue * multiFactor) / ((defense / 100 + 1) * 100)
        return damage.toInt()
    }

    private fun attackPower(damageDealer: Quantomix, effectiv1: Float? = null, effectiv2: Float? = null): Int {
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

    private fun formulaEffectivity(damageDealer: Quantomix, effectiv1: Float?, effectiv2: Float?): Float {
        val target = damageDealer.battleStats.target
        val typ2 =
            requireNotNull(target).typ2 ?: Typ("0") // Setzt "0" als Standard, falls kein zweiter Typ vorhanden ist

        val eff1 = effectivity(target.typ1, requireNotNull(damageDealer.battleStats.nextAttack).type, effectiv1)
        val eff2 = effectivity(typ2, requireNotNull(damageDealer.battleStats.nextAttack).type, effectiv2)

        return when {
            eff1 * eff2 == 1.0f -> 1.0f
            eff1 >= eff2 -> eff1
            else -> eff2
        }
    }

    private fun effectivity(quantomixType: Typ, attackTyp: Typ, effective: Float? = null): Float {
        if (effective != null) {
            return effective
        } else {
            if (quantomixType.name == "0") {
                return 1.0f
            }
            return GameData.getEffektivitaet(quantomixType.name, attackTyp.name)
        }
    }

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

    fun start(effectiv: List<Float>? = null): List<Quantomix> {
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
}