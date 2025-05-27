package hwr.oop.quantomix.Memory

import hwr.oop.quantomix.csvparser.Translator

interface Load {
    fun createHelper(): SaveHelper
    fun getHelper(): SaveHelper
}

class CSVLoad : Load {
    lateinit var saveHelper: SaveHelper

    override fun createHelper(
    ): SaveHelper {
        val translator = Translator()
        val saveHelper = SaveHelper(translator.translateToCoach(), trainer2, quantomixAndBattleStatsMap)
        return saveHelper
    }

    override fun getHelper(): SaveHelper {
        return saveHelper
    }
}