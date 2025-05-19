package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.monster.Quantomix

class Effects(
    private val buff: Boolean,
    private val changeStats: Stats,
    private val targetOfChangeStats: Quantomix,
) {
    fun buffsAndDebuffs() {
        targetOfChangeStats.battleStats.changeStats().BuffsDebuffs(changeStats, buff)
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