package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix

class BattleForMorePlayers(private var ListOfQuantomix: MutableList<Quantomix>) {

    internal fun nextAttacker(): MutableList<Quantomix> {
        // sorts the Quantomix according to the speed. At the end of this function is the
        // fasts Quantomix the first in the list.
        return ListOfQuantomix.sortedBy { it.speed }.toMutableList()
    }

    private fun formulaAttackForce(attackDamage: Int, attackValue: Int, multiFactor: Double): Int {
        var damage = attackDamage * attackValue / 100 - 15 * multiFactor
        return damage.toInt()
    }

    private fun attackPower(DamageDealer: Quantomix): Int {
        //ToDo: Attacken ohne Schaden miteinbeziehen?
        var attack = DamageDealer.battleStats!!.nextAttack
        val nextAttacker = DamageDealer
        var result = 0
        if (attack.type == nextAttacker.typ1.name || (nextAttacker.typ2 != null && attack.type == nextAttacker.typ2.name)) {
            result = formulaAttackForce(attack.damage!!, nextAttacker.specialAttack, effectivity(attack))
        } else {
            result = formulaAttackForce(attack.damage!!, nextAttacker.attack, effectivity(attack))
        }
        return result
    }

    private fun effectivity(attack: Attack): Double {
        TODO("Diese Funktion auch mehrspielernutzbar implementieren.")
    }

    fun start(): List<Quantomix> {
        // changes the kp value of all quantomix
        var attackOrder = nextAttacker()
        for (currentDamageDealer in attackOrder) {
            for (currentDamageResever in attackOrder) {
                if (currentDamageDealer.battleStats!!.target == currentDamageResever) {
                    val power = attackPower(currentDamageDealer)
                    if (power <= 0) {
                        currentDamageResever.battleStats!!.battleKp = 0
                        attackOrder.remove(currentDamageResever)
                    } else {
                        currentDamageResever.battleStats!!.battleKp -= power
                    }
                }
            }

        }
        return attackOrder
    }
}