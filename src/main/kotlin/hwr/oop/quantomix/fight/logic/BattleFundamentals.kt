package hwr.oop.quantomix.fight.logic

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
