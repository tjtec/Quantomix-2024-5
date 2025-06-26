package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.Exceptions.DeadQuantomixException
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import kotlinx.serialization.Serializable

@Serializable
class StandardDamageStrategy : DamageStrategy {
  override fun damageFunction(
    attacker: BattleStats,
    target: BattleStats,
    attack: Attack,
  ): Int {
    if (!attacker.isAlive()) {
      throw DeadQuantomixException("The Quantomix which would attack next is already dead!")
    }
    val context = DamageContext(attacker, target, attack)
    return DamageCalculator.calculateDamage(
      context,
      mode = ModeOfDamageCalculation.Simple
    )
  }
}
//ToDo: im CLi soll man aussuchen, welche schadensformel benutzt wird