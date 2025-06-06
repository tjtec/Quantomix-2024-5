package hwr.oop.quantomix.memory

import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

interface Save {
    fun save(
        trainer1: Coach,
        trainer2: Coach,
        quantomixAndBattleStatsMap: MutableMap<Quantomix, BattleStats>
    ): Boolean
}

class CSVSave : Save {
    override fun save(
        trainer1: Coach,
        trainer2: Coach,
        quantomixAndBattleStatsMap: MutableMap<Quantomix, BattleStats>
    ): Boolean {
        return true
    }
}