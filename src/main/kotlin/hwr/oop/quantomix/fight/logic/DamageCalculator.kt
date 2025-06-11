package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.StatusHelper
import kotlin.random.Random

object DamageCalculator {
  fun calculateDamage(
    context: DamageContext,
    mode: ModeOfDamageCalculation,
  ): Int {
    val effectivityMultiplier =
      EffectivityCalculator.calculateEffectivity(context)
    val attackerStats = context.attacker.getStats()
    val targetStats = context.target.getStats()

    val maxDamage = when {
      context.attack.getSpecialAttack() && context.isSelfHit ->
        this.computeDamage(
          attackDamage = context.attack.getDamage(),
          attackValue = attackerStats.getSpecialAttack(),
          defenseValue = attackerStats.getSpecialDefense(),
          multiplier = effectivityMultiplier,
          statusEffect = context.statusEffect
        )

      context.attack.getSpecialAttack() ->
        this.computeDamage(
          attackDamage = context.attack.getDamage(),
          attackValue = attackerStats.getSpecialAttack(),
          defenseValue = targetStats.getSpecialDefense(),
          multiplier = effectivityMultiplier,
          statusEffect = context.statusEffect
        )

      context.isSelfHit ->
        this.computeDamage(
          attackDamage = context.attack.getDamage(),
          attackValue = attackerStats.getAttack(),
          defenseValue = attackerStats.getDefense(),
          multiplier = effectivityMultiplier,
          statusEffect = context.statusEffect
        )

      else ->
        this.computeDamage(
          attackDamage = context.attack.getDamage(),
          attackValue = attackerStats.getAttack(),
          defenseValue = targetStats.getDefense(),
          multiplier = effectivityMultiplier,
          statusEffect = context.statusEffect
        )
    }

    if (maxDamage <= 0) return 0
    return when (mode) {
      ModeOfDamageCalculation.Complex -> damageWithStabFactor(
        damage = Random.nextInt(
          1,
          maxDamage + 1
        ),
        context = context
      )

      ModeOfDamageCalculation.Simple -> damageWithStabFactor(
        damage = maxDamage,
        context = context
      )
    }
  }

  private fun damageWithStabFactor(damage: Int, context: DamageContext): Int {
    val stabFactor = calculateStabFactor(context)
    return (damage * stabFactor).toInt()
  }

  private fun calculateStabFactor(context: DamageContext): Float {
    val moveType = context.attack.getType()
    val attacker = context.attacker
    val primaryType = attacker.getQuantomix().getType1()
    val secondaryType = attacker.getQuantomix().getType2()
    return if (moveType == primaryType ||
      (secondaryType != null && moveType == secondaryType)
    ) 1.5f else 1.0f
  }

  private fun computeDamage(
    attackDamage: Int,
    attackValue: Int,
    defenseValue: Int,
    multiplier: Float,
    statusEffect: StatusHelper,
  ): Int =
    (((attackDamage * attackValue * multiplier) /
        ((defenseValue / 100 + 1) * 100)).toInt() +
        statusEffect.summand) * statusEffect.multiplicator
}