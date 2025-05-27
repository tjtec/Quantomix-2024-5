package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.DeadQuantomixException
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.fight.objects.UsefulInformationForStatus

interface DamageStrategy {
    fun damageFunction(
        attacker: BattleStats,
        target: BattleStats,
        attack: Attack,
        useful: UsefulInformationForStatus?,
    ): Int
}

class StandardDamageStrategy : DamageStrategy {
    private lateinit var attacker: BattleStats
    private lateinit var target: BattleStats
    private lateinit var currentAttack: Attack
    private var usefulInformationForStatus: UsefulInformationForStatus? = null

    override fun damageFunction(
        attacker: BattleStats,
        target: BattleStats,
        attack: Attack,
        useful: UsefulInformationForStatus?,
    ): Int {
        if (!attacker.isAlive()) {
            throw DeadQuantomixException("The Quantomix which would attack next is already dead!")
        }
        else {
            this.attacker = attacker
            this.target = target
            this.currentAttack = attack
            this.usefulInformationForStatus = useful
            return calculateDamage()
        }
    }

    private fun calculateDamage(): Int {
        val effectivityMultiplier = calculateEffectivity()
        currentAttack.calculateStatusEffect(usefulInformationForStatus)

        return if (currentAttack.getSpecialAttack()) {
            formulaAttackForce(
                attackDamage = currentAttack.getDamage(),
                attackValue = attacker.getStats().getSpecialAttack(),
                defense = target.getStats().getSpecialDefense(),
                multiFactor = effectivityMultiplier * statusMultiplier
            )
        } else {
            formulaAttackForce(
                attackDamage = currentAttack.getDamage(),
                attackValue = attacker.getStats().getAttack(),
                defense = target.getStats().getDefense(),
                multiFactor = (effectivityMultiplier * statusMultiplier)
            )
        }
    }


    private fun calculateEffectivity(): Float {
        val effectivity1 = attacker.getQuantomix().getType1().getEffectivity(currentAttack)
        val type2 = attacker.getQuantomix().getType2()

        return if (type2 == null) {
            effectivity1
        } else {
            val effectivity2 = type2.getEffectivity(currentAttack)
            maxOf(effectivity1, effectivity2)
        }
    }

    private fun formulaAttackForce(
        attackDamage: Int,
        attackValue: Int,
        defense: Int,
        multiFactor: Float
    ): Int = ((attackDamage * attackValue * multiFactor) / ((defense / 100 + 1) * 100)).toInt()
}