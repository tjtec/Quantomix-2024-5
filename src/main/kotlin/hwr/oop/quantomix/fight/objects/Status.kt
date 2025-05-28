package hwr.oop.quantomix.fight.objects

import java.util.*

enum class Status {
    NoDamage,
    Poison,
    StrongPoison,
    Combustion,
    Paralysis,
    Sleep,
    Confusion,
    Freeze;

    var alreadyPassedRounds: Int = 0
    var lastStatusDamage: Int = 0
    fun calculateStatusEffect(attack: Attack): StatusHelper {
        when (alreadyPassedRounds) {
            6->alreadyPassedRounds=0
        }
        val status = this
        var statusHelper = StatusHelper()
        when (status) {
            StrongPoison -> {
                when (alreadyPassedRounds) {
                    0 -> lastStatusDamage = 1 / 16
                    else -> lastStatusDamage *= 2
                }
                alreadyPassedRounds++
                statusHelper = StatusHelper(summand = lastStatusDamage)
            }
            Paralysis -> {
                attack.updateSelfDebuffs(
                    Stats(
                        kp = 0,
                        attack = 0,
                        defense = 0,
                        specialAttack = 0,
                        specialDefense = 0,
                        speed = 1 / 2
                    )
                )
                statusHelper = StatusHelper(
                    multiplicator = UsefulInformationForStatus(
                        alreadyPassedRounds = alreadyPassedRounds
                    ).hitParalysis()
                )
                alreadyPassedRounds++
            }
            Sleep -> {
                statusHelper = if (UsefulInformationForStatus(
                        alreadyPassedRounds = alreadyPassedRounds
                    ).roundsWithStatusEffectLeft()
                ) {
                    StatusHelper(multiplicator = 0)
                } else {
                    StatusHelper(multiplicator = 1)
                }
                alreadyPassedRounds++
            }
            Confusion -> {
                if (UsefulInformationForStatus(
                        alreadyPassedRounds = alreadyPassedRounds
                    ).selfHit()
                    && UsefulInformationForStatus(
                        alreadyPassedRounds = alreadyPassedRounds
                    ).roundsWithStatusEffectLeft()){
                    statusHelper= StatusHelper(multiplicator = -1)
                }
                alreadyPassedRounds++
            }
            Freeze, NoDamage -> {
                statusHelper= StatusHelper(multiplicator = 0)
            }
            Combustion -> {
                statusHelper =
                    StatusHelper(summand = 1 / 8)
            }
            Poison -> {
                statusHelper = StatusHelper(summand = 1 / 16)
            }
        }
        return statusHelper
    }
}

class UsefulInformationForStatus(
    var alreadyPassedRounds: Int,
) {
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