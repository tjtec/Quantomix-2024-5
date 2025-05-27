package hwr.oop.quantomix.Memory

interface Load {
    fun createHelper(): SaveHelper
    fun getHelper(): SaveHelper
}

class CSVLoad : Load {
    lateinit var saveHelper: SaveHelper

    override fun createHelper(
    ): SaveHelper {
        TODO(
            "hier ist die gewünschte Vorbereitung" +
                    " val translator = Translator()\n" +
                    "        val saveHelper = SaveHelper(translator.translateToCoach(), trainer2, quantomixAndBattleStatsMap)\n" +
                    "        return saveHelper"
        )
    }

    override fun getHelper(): SaveHelper {
        return saveHelper
    }
}