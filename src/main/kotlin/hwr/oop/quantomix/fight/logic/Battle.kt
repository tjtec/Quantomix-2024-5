package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import hwr.oop.quantomix.stats.GameData

class Battle(private var ListOfQuantomix: MutableList<Quantomix>) {

    internal fun nextAttacker(): MutableList<Quantomix> {
        // sorts the Quantomix according to the speed. At the end of this function is the
        // fast Quantomix the first in the list.
        if (!(ListOfQuantomix.size >= 2)) {
            error("Not enough number of players")
        }
        return ListOfQuantomix.sortedByDescending { it.battleStats.battleSpeed }.toMutableList()
    }

    private fun formulaAttackForce(attackDamage: Int, attackValue: Int, defense: Int, multiFactor: Double): Int {
        val damage = (attackDamage * attackValue * multiFactor) / ((defense / 100 + 1) * 100)
        return damage.toInt()
    }

    private fun attackPower(damageDealer: Quantomix, effectiv1: Double? = null, effectiv2: Double? = null): Int {
        //ToDo: Attacken ohne Schaden miteinbeziehen?
        val attack = damageDealer.battleStats.nextAttack
        val nextAttacker = damageDealer
        when (requireNotNull(attack).type.name == nextAttacker.typ1.name || (nextAttacker.typ2 != null && attack.type.name == nextAttacker.typ2.name)) {
            true -> return formulaAttackForce(
                requireNotNull(attack.damage),
                nextAttacker.battleStats.battleSpecialAttack,
                requireNotNull(nextAttacker.battleStats.target).battleStats.battleSpecialDefense,
                formulaEffectivity(damageDealer, effectiv1, effectiv2)
            )

            false -> return formulaAttackForce(
                requireNotNull(attack.damage),
                nextAttacker.attack,
                requireNotNull(nextAttacker.battleStats.target).battleStats.battleDefense,
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

    private fun hits(damageQuote: Int): Boolean {

        val randomValue = (1..100).random()
        return when (randomValue <= damageQuote) {
            true -> true
            else -> false
        }
    }

    fun start(effectiv: List<Double>? = null): List<Quantomix> {
        val attackOrder = nextAttacker()
        var indexEffektivitieList = if (effectiv == null) null else 0
        val iterator = attackOrder.iterator()
        while (iterator.hasNext()) {
            val currentDamageDealer = iterator.next()
            for (currentDamageReceiver in attackOrder) {
                if (currentDamageDealer.battleStats.target == currentDamageReceiver) {
                    if (hits(requireNotNull(currentDamageDealer.battleStats.nextAttack).damageQuote)) {
                        val power = if (!effectiv.isNullOrEmpty()) {
                            attackPower(
                                currentDamageDealer,
                                effectiv[requireNotNull(indexEffektivitieList)],
                                effectiv[indexEffektivitieList + 1]
                            )
                        } else {
                            attackPower(currentDamageDealer)
                        }
                        if (power >= currentDamageReceiver.battleStats.battleKp) {
                            currentDamageReceiver.battleStats.newKp(power)
                            attackOrder.remove(currentDamageReceiver)
                        } else {
                            currentDamageReceiver.battleStats.newKp(power)
                        }
                        indexEffektivitieList?.let { indexEffektivitieList++ }
                        break
                    } else {
                        TODO("Soll etwas passieren, wenn die Attacke nicht trifft und wenn ja was?")
                    }
                }
            }
        }
        return attackOrder
    }
}