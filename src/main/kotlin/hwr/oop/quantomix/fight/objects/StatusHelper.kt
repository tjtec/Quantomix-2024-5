package hwr.oop.quantomix.fight.objects

import kotlinx.serialization.Serializable

@Serializable
class StatusHelper(
  val multiplicator: Int = 1,
  val summand: Int = 0,
  val alreadyPassedRounds: Int = 0,
) {
  fun selfHit(fixMultipier: Int = 0): Boolean {
    return when (fixMultipier) {
      0 -> when (multiplicator) {
        -1 -> true
        else -> false
      }

      else -> true
    }
  }
}