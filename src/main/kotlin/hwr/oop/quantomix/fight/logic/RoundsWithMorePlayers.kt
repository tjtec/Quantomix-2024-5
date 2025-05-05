package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class RoundsWithMorePlayers(val trainer: List<Coach>) {
    fun start(): Coach {
        val listOfQuantomixInBattle = mutableListOf<Quantomix>()
        val numberOfPlayers = trainer.size
        for (currentPlayer: Coach in trainer) {
            val attack = askForAttack(currentPlayer)
            val target = askForTarget(currentPlayer)
            val stats = BattleStats(
                currentPlayer.quantomix1.kp,
                currentPlayer.quantomix1.attack,
                currentPlayer.quantomix1.defense,
                currentPlayer.quantomix1.specialAttack,
                currentPlayer.quantomix1.specialDefense,
                currentPlayer.quantomix1.speed,
                target,
                attack,
                currentPlayer
            )
            currentPlayer.quantomix1.battleStats = stats
            listOfQuantomixInBattle.add(currentPlayer.quantomix1)
        }
        var battle = BattleForMorePlayers(listOfQuantomixInBattle)
        var winner = round(battle, listOfQuantomixInBattle, numberOfPlayers)
        return trainer[winner]
    }

    private fun round(
        battle: BattleForMorePlayers,
        listOfPreviusQuantomixInBattle: MutableList<Quantomix>,
        numberOfPlayers: Int
    ): Int {
        val QuantomixLeftInBattle = battle.start()
        val numberOfQuantomixPerPlayerLeft = MutableList(numberOfPlayers) { 6 }
        var indexForNumberOfQuantomixPerPlayerLeft = 0
        val QuantomixInTheNextRound = mutableListOf<Quantomix>()
        while (numberOfQuantomixPerPlayerLeft.count { it != 0 } != 1) {
            if (QuantomixLeftInBattle.size < listOfPreviusQuantomixInBattle.size) {
                for (currentQuantomix in listOfPreviusQuantomixInBattle) {
                    if (!QuantomixLeftInBattle.contains(currentQuantomix)) {
                        var next = nextQuantomix(currentQuantomix.battleStats!!.trainer)
                        if (next != null) {
                            QuantomixInTheNextRound.add(next)
                            numberOfQuantomixPerPlayerLeft[indexForNumberOfQuantomixPerPlayerLeft] -= 1
                        }
                    } else {
                        QuantomixInTheNextRound.add(currentQuantomix)
                    }
                    currentQuantomix.battleStats!!.nextAttack = askForAttack(currentQuantomix.battleStats!!.trainer)
                    currentQuantomix.battleStats!!.target = askForTarget(currentQuantomix.battleStats!!.trainer)
                    indexForNumberOfQuantomixPerPlayerLeft += 1
                }
            } else {
                for (currentQuantomix in listOfPreviusQuantomixInBattle) {
                    var changedCurrentQuantomix =
                        DoYouWantToChangeTheCurrentQuantomix(currentQuantomix.battleStats!!.trainer)
                    changedCurrentQuantomix.battleStats!!.nextAttack =
                        askForAttack(changedCurrentQuantomix.battleStats!!.trainer)
                    changedCurrentQuantomix.battleStats!!.target =
                        askForTarget(changedCurrentQuantomix.battleStats!!.trainer)
                }
            }
        }
        return numberOfQuantomixPerPlayerLeft.indexOfFirst { it != 0 }
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
                    "soll diese Funktion null zurückgeben."
        )
    }

    private fun DoYouWantToChangeTheCurrentQuantomix(player: Coach): Quantomix {
        TODO("jeder Spieler wird gefragt, ob er mit dem Eingesetzten Quantomix weiterkämpfen möchte")
    }
}