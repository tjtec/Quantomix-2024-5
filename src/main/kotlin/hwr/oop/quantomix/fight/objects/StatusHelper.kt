package hwr.oop.quantomix.fight.objects

class StatusHelper(
  val multiplicator: Int = 1,
  val summand: Int = 0,
  val alreadyPassedRounds: Int = 0,
) {
  fun selfHit(): Boolean {
    return when (multiplicator) {
      -1 -> true
      else -> false
    }
  }
}