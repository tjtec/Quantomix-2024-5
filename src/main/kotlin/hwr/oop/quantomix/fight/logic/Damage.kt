package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.fight.objects.Status

interface DamageStrategy {
    fun damageFunction(
        attacker: BattleStats,
        target: BattleStats,
        attack: Attack
    ): Int
}

class StandardDamageStrategy : DamageStrategy {
    private lateinit var attacker: BattleStats
    private lateinit var target: BattleStats
    private lateinit var currentAttack: Attack

    override fun damageFunction(
        attacker: BattleStats,
        target: BattleStats,
        attack: Attack
    ): Int {
        if (attacker.getStats().getKp() == 0){
            throw
        }
        else {
            this.attacker = attacker
            this.target = target
            this.currentAttack = attack

            return calculateDamage()
        }
    }

    private fun calculateDamage(): Int {
        val effectivityMultiplier = calculateEffectivity()
        val statusMultiplier = calculateStatusEffect()

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
                multiFactor = effectivityMultiplier * statusMultiplier
            )
        }
    }

    private fun calculateStatusEffect(): Float {
        return when (currentAttack.getStatus()) {
            Status.NoDamage -> 0f
            Status.Poison -> 0.0625f    // 1/16
            Status.StrongPoison -> 0.0625f
            Status.Combustion -> 0.125f  // 1/8
            Status.Sleep -> 0f
            Status.Freeze -> 0f
            null -> 1f
            else -> 1f
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