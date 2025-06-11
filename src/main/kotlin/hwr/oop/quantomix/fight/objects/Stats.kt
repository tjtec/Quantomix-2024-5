package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.Exceptions.NoBuffOrDebuffValue
import kotlinx.serialization.Serializable

@Serializable
class Stats(
  private var kp: Int,
  private var attack: Int,
  private var defense: Int,
  private var specialAttack: Int,
  private var specialDefense: Int,
  private var speed: Int,
) {
  fun deepCopy(
    kp: Int = this.kp,
    attack: Int = this.attack,
    defense: Int = this.defense,
    specialAttack: Int = this.specialAttack,
    specialDefense: Int = this.specialDefense,
    speed: Int = this.speed,
  ) = Stats(
    kp, attack, defense, specialAttack, specialDefense, speed
  )

  fun takeDamage(damage: Int) {
    this.kp = when ((this.kp - damage) <= 0) {
      true -> 0
      else -> this.kp - damage
    }
  }

  fun buffsDebuffs(stats: Stats, heal: Boolean) {
    if (!heal) {
      selfDamage(stats)
    } else {
      heal(stats)
    }
    this.attack = effectOfDeBuff(stats.getAttack(), this.attack)
    this.defense = effectOfDeBuff(stats.getDefense(), this.defense)
    this.specialAttack =
      effectOfDeBuff(stats.getSpecialAttack(), this.specialAttack)
    this.specialDefense =
      effectOfDeBuff(stats.getSpecialDefense(), this.specialDefense)
    this.speed = effectOfDeBuff(stats.getSpeed(), this.speed)
  }

  private fun selfDamage(stats: Stats) {
    val solution = this.kp - stats.kp
    if (solution < 0) {
      this.kp = 0
    } else {
      this.kp = solution
    }
  }

  private fun heal(stats: Stats) {
    this.kp += stats.kp
  }

  fun reduceSpeed(parameter: Double) {
    this.speed -= (this.speed * parameter).toInt()
  }

  fun getKp(): Int = this.kp
  fun getSpeed(): Int = this.speed
  fun getAttack(): Int = this.attack
  fun getDefense(): Int = this.defense
  fun getSpecialAttack(): Int = this.specialAttack
  fun getSpecialDefense(): Int = this.specialDefense

  private fun effectOfDeBuff(
    effectLevel: Int,
    oldValue: Int,
  ): Int {
    var multiplier: Double
    when (effectLevel) {
      -6 -> multiplier = 0.25
      -5 -> multiplier = 0.29
      -4 -> multiplier = 0.33
      -3 -> multiplier = 0.4
      -2 -> multiplier = 0.5
      -1 -> multiplier = 0.67
      0 -> multiplier = 1.0
      1 -> multiplier = 1.5
      2 -> multiplier = 2.0
      3 -> multiplier = 2.5
      4 -> multiplier = 3.0
      5 -> multiplier = 3.5
      6 -> multiplier = 4.0
      else -> throw NoBuffOrDebuffValue("The number $effectLevel is a not allowed number for Buffs and Debuffs.")
    }
    return (oldValue * multiplier).toInt()
  }
}
