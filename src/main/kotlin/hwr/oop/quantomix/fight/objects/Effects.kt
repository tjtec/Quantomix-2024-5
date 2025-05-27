package hwr.oop.quantomix.fight.objects

class Effects(
    private val buff: Boolean,
    private val changeStats: Stats,
    // private val targetOfChangeStats: BattleStats,
    private val self: Boolean,
) {
    fun buffsAndDebuffs(targetOfChangeStats: BattleStats) {
        targetOfChangeStats.changeStats().buffsDebuffs(changeStats, buff)
    }

    fun isSelfDebuff(): Boolean {
        return !buff && self
    }
    fun getSelf(): Boolean = self

    fun upDateEffect(newStats: Stats) {
        this.changeStats.fueseToStats(newStats)
    }
}

/*enum class Status {
    NoDamage,
    Poisoning,
    StrongPoisoning,
    Combustion,
    Paralysis,
    Sleep,
    Confusion,
    Freeze;
}*/