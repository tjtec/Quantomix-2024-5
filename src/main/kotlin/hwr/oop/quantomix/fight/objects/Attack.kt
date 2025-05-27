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

    fun updateSelfDebuffs(stats: Stats): Boolean {
        val indexCurrentEffect = 0
        while (indexCurrentEffect < this.effects.size) {
            if (effects[indexCurrentEffect].isSelfDebuff()) {
                effects[indexCurrentEffect].upDateEffect(stats)
                return true
            }
        }
        return false
    }

    fun changeStatsAndStatus(attacker: BattleStats, target: BattleStats): Boolean {
        var alreadyChangedEffects = 0
        var successful = false
        while (!(this.getEffects().isEmpty()) && this.getEffects().size > alreadyChangedEffects) {
            if (this.getEffects()[alreadyChangedEffects].getSelf()) {
                successful = this.getEffects()[alreadyChangedEffects].buffsAndDebuffs(attacker)
            } else {
                successful = this.getEffects()[alreadyChangedEffects].buffsAndDebuffs(target)
            }
            alreadyChangedEffects += 1
        }
        val status = this.getStatus()
        if (status != null) {
            successful = target.changeStatus(status)
        }
        return successful
    }

    fun hits(): Boolean {
        val randomValue = Random().nextInt(1, 100)
        //val randomValue = (1..100).random()
        return when (randomValue <= this.getDamageQuote()) {
            true -> true
            else -> false
        }
    }

    fun hasStatus(): Boolean {
        return this.status != null
    }

}