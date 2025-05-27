package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.objects.Typ
import java.util.*

data class Attack(
    private val attackName: String,
    private val type: Typ,
    private val damage: Int,
    private val damageQuote: Int,
    private val specialAttack: Boolean,
    private val effects: MutableList<Effects>,
    private val status: Status? = null
) {
    fun getType(): Typ {
        return this.type
    }

    fun getDamage(): Int {
        return this.damage
    }

    fun getSpecialAttack(): Boolean {
        return this.specialAttack
    }

    fun getDamageQuote(): Int {
        return this.damageQuote
    }

    fun getEffects(): MutableList<Effects> {
        return this.effects
    }

    fun getStatus(): Status? {
        return this.status
    }

    fun updateSelfDebuffs(stats: Stats) {
        val indexCurrentEffect = 0
        while (indexCurrentEffect < this.effects.size) {
            if (effects[indexCurrentEffect].isSelfDebuff()) {
                effects[indexCurrentEffect].upDateEffect(stats)
            }
        }
    }

    fun changeStats(attacker: BattleStats, target: BattleStats) {
        var alreadyChangedEffects = 0
        while (!(this.getEffects().isEmpty()) && this.getEffects().size > alreadyChangedEffects) {
            if (this.getEffects()[alreadyChangedEffects].getSelf()) {
                this.getEffects()[alreadyChangedEffects].buffsAndDebuffs(attacker)
            }
            this.getEffects()[alreadyChangedEffects].buffsAndDebuffs(target)
            alreadyChangedEffects += 1
        }
        val status = this.getStatus()
        if (status != null) {
            target.changeStatus(status)
        }
    }

    fun hits(): Boolean {
        val randomValue = Random().nextInt(1, 100)
        //val randomValue = (1..100).random()
        return when (randomValue <= this.getDamageQuote()) {
            true -> true
            else -> false
        }
    }

    fun calculateStatusEffect(usefulInformationForStatus1: UsefulInformationForStatus?): StatusHelper {
        var usefulInformationForStatus: UsefulInformationForStatus? = usefulInformationForStatus1
        val status = this.getStatus()
        var kpDamage = 0.0f
        val statusHelper = StatusHelper(usefulInformationForStatus = usefulInformationForStatus)
        if (status == Status.StrongPoison) {
            if (usefulInformationForStatus == null) {
                usefulInformationForStatus = UsefulInformationForStatus(
                    alreadyPassedRounds = 0,
                    lastStatusDamage = 0.0625f
                )
                statusHelper.setHelper(overrideSummand = 0.0625f)
            } else {
                kpDamage = usefulInformationForStatus.lastStatusDamage * 2
                usefulInformationForStatus.alreadyPassedRounds += 1
                usefulInformationForStatus.lastStatusDamage = kpDamage
            }
        }
        if (status == Status.Paralysis) {
            this.updateSelfDebuffs(
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
        if (status == Status.Sleep) {
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
        if (status == Status.Confusion) {
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

        when (this.getStatus()) {
            Status.NoDamage -> statusHelper.setHelper(overrideMuliplicator = 0)
            Status.Poison -> statusHelper.setHelper(overrideSummand = 0.0625f)   // 1/16
            Status.Combustion -> statusHelper.setHelper(overrideSummand = 0.125f)  // 1/8
            Status.Freeze -> statusHelper.setHelper(overrideMuliplicator = 0)
            else -> 1
        }
        s
        return statusHelper
    }
}

class StatusHelper(
    val multiplicator: Int = 1,
    val summand: Float = 0.0f,
    val usefulInformationForStatus: UsefulInformationForStatus? = null
) {
    fun setHelper(
        overrideMuliplicator: Int = multiplicator,
        overrideSummand: Float = summand,
        overrideUsefulInformationForStatus: UsefulInformationForStatus? = usefulInformationForStatus
    ): StatusHelper {
        return StatusHelper(overrideMuliplicator, overrideSummand)
    }

    fun selfHit(): Boolean {
        return when (multiplicator) {
            -1 -> true
            else -> false
        }
    }
}
