package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class Rounds(val trainer: List<Coach>) {
    fun start(numberOfQuantomixPerTrainer: Int = 1): Coach {
        val listOfQuantomixInBattle = mutableListOf<Quantomix>()
        val numberOfPlayers = trainer.size
        for (currentPlayer: Coach in trainer) {
            for (i in 0 until numberOfQuantomixPerTrainer) {
                requireNotNull(currentPlayer.quantomixTeam[i].battleStats).newAttack(askForAttack(currentPlayer))
                requireNotNull(currentPlayer.quantomixTeam[i].battleStats).newTarget(askForTarget(currentPlayer))
                listOfQuantomixInBattle.add(currentPlayer.quantomixTeam[i])

            }
        }
        val battle = Battle(listOfQuantomixInBattle)
        var winner = round(
            battle, listOfQuantomixInBattle,
            numberOfPlayers, numberOfQuantomixPerTrainer
        )
        return trainer[winner]
    }

    private fun round(
        battle: Battle,
        listOfPreviusQuantomixInBattle: MutableList<Quantomix>,
        numberOfPlayers: Int,
        numberOfPlayersPerQuantomix: Int,
    ): Int {
        val quantomixLeftInBattle = battle.start()
        val numberOfQuantomixPerPlayerLeft = MutableList(numberOfPlayers) { 6 }
        var indexForNumberOfQuantomixPerPlayerLeft = 0
        val quantomixInTheNextRound = mutableListOf<Quantomix>()
        var maximumQuantomixInBattle = listOfPreviusQuantomixInBattle.size
        while (numberOfQuantomixPerPlayerLeft.count { it != 0 } != 1) {
            var numberOfQuantomixPerPlayerInBattleLeftToCheck = numberOfPlayersPerQuantomix
            if (quantomixLeftInBattle.size < maximumQuantomixInBattle) {
                for (currentQuantomix in listOfPreviusQuantomixInBattle) {
                    if (!quantomixLeftInBattle.contains(currentQuantomix)) {
                        val next = nextQuantomix(currentQuantomix.battleStats.trainer!!)
                        if (next != null) {
                            quantomixInTheNextRound.add(next)
                            numberOfQuantomixPerPlayerLeft[indexForNumberOfQuantomixPerPlayerLeft] -= 1
                        } else {
                            maximumQuantomixInBattle -= 1
                        }
                    } else {
                        askPlayer(currentQuantomix.battleStats.trainer!!)
                        quantomixInTheNextRound.add(currentQuantomix)
                    }
                    indexForNumberOfQuantomixPerPlayerLeft += when (numberOfQuantomixPerPlayerInBattleLeftToCheck) {
                        1 -> 1
                        else -> 0
                    }
                    numberOfQuantomixPerPlayerInBattleLeftToCheck =
                        when (numberOfQuantomixPerPlayerInBattleLeftToCheck) {
                            1 -> numberOfPlayersPerQuantomix
                            else -> numberOfQuantomixPerPlayerInBattleLeftToCheck - 1
                        }
                }
                indexForNumberOfQuantomixPerPlayerLeft = 0
            } else {
                for (currentQuantomix in listOfPreviusQuantomixInBattle) {
                    askPlayer(currentQuantomix.battleStats.trainer!!)
                    quantomixInTheNextRound.add(currentQuantomix)
                }
                val nextBattle = Battle(quantomixInTheNextRound)
                nextBattle.start()
            }
        }
        return numberOfQuantomixPerPlayerLeft.indexOfFirst { it != 0 }
    }

    private fun askPlayer(player: Coach, dead: Boolean = false) {
        if (!dead) {
            val changedCurrentQuantomix =
                doYouWantToChangeTheCurrentQuantomix(player)
            changedCurrentQuantomix.battleStats.newAttack(
                askForAttack(changedCurrentQuantomix.battleStats.trainer!!)
            )
            changedCurrentQuantomix.battleStats.newTarget(
                askForTarget(changedCurrentQuantomix.battleStats.trainer!!)
            )
        }
    }

    private fun askForAttack(player: Coach): Attack {
        TODO(
            "Diese Funktion soll eine andere Funktion aufrufen, welche den " +
                    "Spieler zu dem Angriff befragt und dieses an diese Funktion übergibt. " +
                    "Diese Funktion gibt dann die Attacke zurück"
        )
    }

    private fun askForTarget(player: Coach): Quantomix {
        TODO(
            "Diese Funktion soll eine andere Funktion aufrufen, welche den " +
                    "Spieler zum Ziel der Attacke befragt und dieses an diese Funktion übergibt. " +
                    "Diese Funktion gibt dann das Ziel der Attacke  zurück"
        )
    }

    private fun nextQuantomix(player: Coach): Quantomix? {
        TODO(
            "Diese Funktion fragt, welches Quantomix als nächstes eingesetzt werden soll," +
                    "(dies geschieht wie bei den obrigen Funktionen). Ist kein Quantomix mehr übrig" +
                    "soll diese Funktion null zurückgeben. Muss die BattleStats mitsetzen"
        )
    }

    private fun doYouWantToChangeTheCurrentQuantomix(player: Coach): Quantomix {
        TODO("jeder Spieler wird gefragt, ob er mit dem Eingesetzten Quantomix weiterkämpfen möchte")
        //askPlayer(currentQuantomix.battleStats!!.trainer, true) um die BattleStats entsprechent zu setzen
    }
}