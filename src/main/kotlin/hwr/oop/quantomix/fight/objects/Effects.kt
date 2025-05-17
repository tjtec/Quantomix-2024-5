package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.monster.Quantomix

class Effects(
    val buff: Boolean,
    val changeStats: Stats,
    val targetOfChangeStats: Quantomix,
) {
    fun buffsAndDebuffs() {
        targetOfChangeStats.battleStats.stats.BuffsDebuffs(changeStats, buff)
    }
}