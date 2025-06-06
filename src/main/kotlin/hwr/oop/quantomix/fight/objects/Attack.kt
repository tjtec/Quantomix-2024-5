package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.objects.Typ
import java.util.*

data class Attack(
    private val attackName: String,
    private val type: Typ,
    private val damage: Int,
    private val damageQuote: Int,
    private val specialAttack: Boolean,
    private val effects: MutableList<Effects> = mutableListOf(),
    private val status: Status? = null,
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

  fun changeStatsAndStatus(
      attacker: BattleStats,
      target: BattleStats,
  ): Boolean {
    var alreadyChangedEffects = 0
    var successful = false
    while (!(this.getEffects()
        .isEmpty()) && this.getEffects().size > alreadyChangedEffects
    ) {
      if (this.getEffects()[alreadyChangedEffects].hitsAttacker()) {
        this.getEffects()[alreadyChangedEffects].buffsAndDebuffs(attacker)
      successful = true
      } else if (!this.getEffects()[alreadyChangedEffects].hitsAttacker()) {
        this.getEffects()[alreadyChangedEffects].buffsAndDebuffs(target)
      successful = true
      }
      else{
        successful = false
      }
      alreadyChangedEffects += 1
    }
    if (this.status != null) {
      successful = target.changeStatus(status)
    }
    return successful
  }

  fun hits(randomValue:Int = Random().nextInt(1, 100)): Boolean {
    return when (randomValue <= this.getDamageQuote()) {
      true -> true
      else -> false
    }
  }

  fun hasStatus(): Boolean {
    return this.status != null
  }

}