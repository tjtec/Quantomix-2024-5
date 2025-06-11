package hwr.oop.quantomix.fight.objects

import kotlinx.serialization.Serializable

@Serializable
class Effects(
  private val heal: Boolean,
  private val changeStats: Stats,
  private val self: Boolean,
) {
  fun buffsAndDebuffs(targetOfChangeStats: BattleStats) {
    targetOfChangeStats.getStats().buffsDebuffs(changeStats, heal)
  }

  fun hitsAttacker(): Boolean = self
}