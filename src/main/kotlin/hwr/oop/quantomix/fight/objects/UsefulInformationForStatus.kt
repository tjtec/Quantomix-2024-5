package hwr.oop.quantomix.fight.objects

import java.util.Random

class UsefulInformationForStatus(var alreadyPassedRounds: Int,) {
    var randomValueDuration = Random().nextInt(2, 5)

    fun hitParalysis(): Int {
        val randomValue = Random().nextInt(0, 100)
        return when (randomValue < (2 / 3 * 100)) {
            true -> 1
            false -> 0
        }
    }

    private fun newDuration() {
        randomValueDuration = Random().nextInt(2, 5)
    }

    fun selfHit(): Boolean {
        val randomValue = Random().nextInt(0, 100)
        return randomValue > 50
    }

    fun roundsWithStatusEffectLeft(): Boolean {
        if (alreadyPassedRounds > randomValueDuration) {
            newDuration()
            alreadyPassedRounds = 0
            return true
        }
        return false
    }
}