package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.Exceptions.DeadQuantomixException
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.fight.objects.StatusHelper

interface DamageStrategy {
  fun damageFunction(
    attacker: BattleStats,
    target: BattleStats,
    attack: Attack,
  ): Int
}

class StandardDamageStrategy : DamageStrategy {
  private lateinit var attacker: BattleStats
  private lateinit var target: BattleStats
  private lateinit var currentAttack: Attack

  override fun damageFunction(
    attacker: BattleStats,
    target: BattleStats,
    attack: Attack,
  ): Int {
    if (!attacker.isAlive()) {
      throw DeadQuantomixException("The Quantomix which would attack next is already dead!")
    } else {
      this.attacker = attacker
      this.target = target
      this.currentAttack = attack
      return calculateDamage()
    }
  }

  private fun calculateDamage(): Int {
    val effectivityMultiplier = calculateEffectivity()
    val statusEffect: StatusHelper = if (currentAttack.hasStatus()) {
      target.changesAccordingToStatus()
    } else {
      StatusHelper()
    }
    return if (currentAttack.getSpecialAttack()) {
      formulaAttackForce(
        attackDamage = currentAttack.getDamage() + statusEffect.summand * statusEffect.multiplicator,
        attackValue = attacker.getStats().getSpecialAttack(),
        defense = target.getStats().getSpecialDefense(),
        multiFactor = effectivityMultiplier
      )
    } else {
      formulaAttackForce(
        attackDamage = currentAttack.getDamage() + statusEffect.summand * statusEffect.multiplicator,
        attackValue = attacker.getStats().getAttack(),
        defense = target.getStats().getDefense(),
        multiFactor = effectivityMultiplier
      )
    }
  }


  private fun calculateEffectivity(): Float {
    val effectivity1 =
      target.getQuantomix().getType1().getEffectivity(currentAttack)
    val type2 = target.getQuantomix().getType2()

    return if (type2 == null) {
      effectivity1
    } else {
      val effectivity2 = type2.getEffectivity(currentAttack)
      when (effectivity1 >= effectivity2) {
        true -> effectivity1
        false -> effectivity2
      }
    }
  }

  private fun formulaAttackForce(
      attackDamage: Int,
      attackValue: Int,
      defense: Int,
      multiFactor: Float,
  ): Int = ((attackDamage * attackValue * multiFactor)
      / ((defense / 100 + 1) * 100)).toInt()
}
//ToDo: 2 schadensformeln gleichzeitig in benutzung die zweite soll schauen innerhalb des berechneten Schaden wie viel schaden entsteht (mit randomasation)
//ToDo: im CLi soll man aussuchen, welche schadensformel benutzt wird