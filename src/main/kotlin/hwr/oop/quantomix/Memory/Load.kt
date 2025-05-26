package hwr.oop.quantomix.Memory

interface Load {
    fun load(
        trainer1: Coach,
        trainer2: Coach,
        quantomixAndBattleStatsMap: mutableMabOf<Quantomix, BattleStats>
    ): SaveHelper
}

class CSVLoad : Load {
    override fun load(
        trainer1: Coach,
        trainer2: Coach,
        quantomixAndBattleStatsMap: mutableMabOf<Quantomix, BattleStats>
    ) {

    }
}