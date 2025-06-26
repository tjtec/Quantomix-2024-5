package hwr.oop.quantomix.memory

import hwr.oop.quantomix.fight.logic.BattleFundamentals
import hwr.oop.quantomix.fight.logic.ModeOfDamageCalculation
import kotlinx.serialization.Serializable


@Serializable
data class BattleFundamentalsDTO(
  val numberOfQuantomixInRound: Int = 0,
  val damageStrategy: ModeOfDamageCalculation = ModeOfDamageCalculation.Simple,
  )
