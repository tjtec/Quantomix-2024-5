package hwr.oop.quantomix.fight.logic

import kotlinx.serialization.Serializable

@Serializable
object BattleFundamentals {
  var numberOfQuantomixInRound: Int = 1
  var damageStrategy: ModeOfDamageCalculation= ModeOfDamageCalculation.Simple
  fun correctDamageStrategy():DamageStrategy{
    return when(damageStrategy){
      ModeOfDamageCalculation.Simple -> StandardDamageStrategy()
      ModeOfDamageCalculation.Complex -> ComplexDamageStrategy()
    }
  }
}
