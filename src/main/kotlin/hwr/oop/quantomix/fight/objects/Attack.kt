package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.objects.Typ

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

    fun getOneEffect(index: Int): Effects {
        return this.effects[index]
    }

    fun getStatus(): Status? {
        return this.status
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
}
