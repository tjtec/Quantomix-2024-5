package hwr.oop.quantomix.Memory

interface Save {
    fun save(
    trainer1:Coach,
    trainer2:Coach,
    quantomixAndBattleStatsMap:mutableMabOf<Quantomix, BattleStats>
    ):Boolean
}

class CSVSave: Save{
    override fun save(trainer1:Coach,
                          trainer2:Coach,
                          quantomixAndBattleStatsMap:mutableMabOf<Quantomix, BattleStats>)
     {

     }
}