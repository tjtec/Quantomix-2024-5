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

    fun calculateStatusEffect(attack: Attack, usefulInformationForStatus1: UsefulInformationForStatus?): StatusHelper {
        var usefulInformationForStatus: UsefulInformationForStatus? = usefulInformationForStatus1
        val status = this
        var kpDamage = 0.0f
        val statusHelper = StatusHelper(usefulInformationForStatus = usefulInformationForStatus)
        if (status == StrongPoison) {
            if (usefulInformationForStatus == null) {
                usefulInformationForStatus = UsefulInformationForStatus(
                    alreadyPassedRounds = 0,
                    lastStatusDamage = 0.0625f
                )
                statusHelper.setHelper(overrideSummand = 1 / 16)
            } else {
                kpDamage = usefulInformationForStatus.lastStatusDamage * 2
                usefulInformationForStatus.alreadyPassedRounds += 1
                usefulInformationForStatus.lastStatusDamage = kpDamage
            }
        }
        if (status == Paralysis) {
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
            if (usefulInformationForStatus == null) {
                usefulInformationForStatus =
                    UsefulInformationForStatus(alreadyPassedRounds = 0, lastStatusDamage = 0.0f)
                statusHelper.setHelper(overrideMuliplicator = usefulInformationForStatus.hitParalysis())
                usefulInformationForStatus.alreadyPassedRounds += 1
            }
        }
        if (status == Sleep) {
            if (usefulInformationForStatus == null) {
                usefulInformationForStatus =
                    UsefulInformationForStatus(alreadyPassedRounds = 0, lastStatusDamage = 0.00f)
            } else {
                usefulInformationForStatus.alreadyPassedRounds += 1
            }
            if (usefulInformationForStatus.roundsWithStatusEffectLeft()) {
                statusHelper.setHelper(overrideMuliplicator = 0)
            } else {
                statusHelper.setHelper(overrideMuliplicator = 1)
            }
        }
        if (status == Confusion) {
            if (usefulInformationForStatus == null) {
                usefulInformationForStatus =
                    UsefulInformationForStatus(alreadyPassedRounds = 0, lastStatusDamage = 0.00f)
            } else {
                usefulInformationForStatus.alreadyPassedRounds += 1
            }
            if (usefulInformationForStatus.selfHit() && usefulInformationForStatus.roundsWithStatusEffectLeft()) {
                statusHelper.setHelper(overrideMuliplicator = -1)
            }
        }

        when (this) {
            NoDamage -> statusHelper.setHelper(overrideMuliplicator = 0)
            Poison -> statusHelper.setHelper(overrideSummand = 1 / 16)   // 1/16
            Combustion -> statusHelper.setHelper(overrideSummand = 1 / 8)  // 1/8
            Freeze -> statusHelper.setHelper(overrideMuliplicator = 0)
            else -> 1
        }
        return statusHelper
    }
}

class UsefulInformationForStatus(
    var alreadyPassedRounds: Int,
    var lastStatusDamage: Float,
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