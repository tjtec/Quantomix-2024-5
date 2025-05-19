package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.fight.logic.BattleStats

class Effects(
    private val buff: Boolean,
    private val changeStats: Stats,
    private val targetOfChangeStats: BattleStats,
) {
    fun buffsAndDebuffs() {
        targetOfChangeStats.changeStats().BuffsDebuffs(changeStats, buff)
    }
}

enum class Status {
    NoDamage,
    Poisoning,
    StrongPoisoning,
    Combustion,
    Paralysis,
    Sleep,
    Confusion,
    Freeze;
}