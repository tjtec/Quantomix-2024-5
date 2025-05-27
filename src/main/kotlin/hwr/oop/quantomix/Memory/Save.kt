package hwr.oop.quantomix.Memory

import hwr.oop.quantomix.objects.Coach

interface Save {
    fun save(
        trainer1: Coach,
        trainer2: Coach,
        quantomixAndBattleStatsMap: mutableMabOf<Quantomix, BattleStats>
    ):Boolean
}

class CSVSave: Save{
    override fun save(trainer1:Coach,
                          trainer2:Coach,
                      quantomixAndBattleStatsMap: mutableMabOf<Quantomix, BattleStats>
    ): Boolean {

     }
}