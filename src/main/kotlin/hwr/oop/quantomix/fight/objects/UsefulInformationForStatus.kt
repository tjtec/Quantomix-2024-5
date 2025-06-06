package hwr.oop.quantomix.fight.objects

import java.util.*

class UsefulInformationForStatus( private var alreadyPassedRounds: Int) {

  fun hitParalysis(randomValue:Int = Random().nextInt(0, 100)): Int {
    return when (randomValue < (0.66666 * 100)) {
        true -> 1
        false -> 0
      }
  }

  fun selfHit(randomValue:Int = Random().nextInt(0, 100)): Boolean {
    return randomValue > 50
  }

  fun roundsWithStatusEffectLeft(randomValueDuration:Int = Random().nextInt(2, 5)): Boolean {
    if (alreadyPassedRounds > randomValueDuration) {
      alreadyPassedRounds = 0
      return false
    }
    return true
  }
}