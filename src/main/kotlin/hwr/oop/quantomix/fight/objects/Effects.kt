package hwr.oop.quantomix.fight.objects

class Effects(
    private val buff: Boolean,
    private val changeStats: Stats,
    // private val targetOfChangeStats: BattleStats,
    private val self: Boolean,
) {
    fun buffsAndDebuffs(targetOfChangeStats: BattleStats) {
        targetOfChangeStats.changeStats().BuffsDebuffs(changeStats, buff)
    }

    fun getSelf(): Boolean = self
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