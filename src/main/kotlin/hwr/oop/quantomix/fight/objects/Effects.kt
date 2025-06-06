package hwr.oop.quantomix.fight.objects

class Effects(
  private val buff: Boolean,
  private val changeStats: Stats,
  private val self: Boolean,
) {
  fun buffsAndDebuffs(targetOfChangeStats: BattleStats) {
    targetOfChangeStats.getStats().buffsDebuffs(changeStats, buff)
  }

  fun hitsAttacker(): Boolean = self
}