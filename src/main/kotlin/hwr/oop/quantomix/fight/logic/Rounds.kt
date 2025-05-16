package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class Rounds(val trainer: List<Coach>) {
    fun start(mutableListOfAttack: List<Attack>? =null, numberOfQuantomixPerTrainer: Int = 1): Coach {
        val listOfQuantomixInBattle = mutableListOf<Quantomix>()
        val numberOfPlayers = trainer.size
        for (currentPlayer: Coach in trainer) {
            for (i in 0 until numberOfQuantomixPerTrainer) {
                requireNotNull(currentPlayer.quantomixTeam[i].battleStats).newAttack(askForAttack(mutableListOfAttack, currentPlayer))
                requireNotNull(currentPlayer.quantomixTeam[i].battleStats).newTarget(askForTarget(currentPlayer))
                listOfQuantomixInBattle.add(currentPlayer.quantomixTeam[i])

            }
        }
        val battle = Battle(listOfQuantomixInBattle)
        val winner = round(
            battle, listOfQuantomixInBattle,
            numberOfPlayers, numberOfQuantomixPerTrainer, mutableListOfAttack
        )
        return trainer[winner]
    }

    private fun round(
        battle: Battle,
        listOfPreviusQuantomixInBattle: MutableList<Quantomix>,
        numberOfPlayers: Int,
        numberOfPlayersPerQuantomix: Int,
        mutableListOfAttack: List<Attack>?
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
                        askPlayer(currentQuantomix.battleStats.trainer!!, mutableListOfAttack)
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
                    askPlayer(currentQuantomix.battleStats.trainer!!, mutableListOfAttack)
                    quantomixInTheNextRound.add(currentQuantomix)
                }
                val nextBattle = Battle(quantomixInTheNextRound)
                nextBattle.start()
            }
        }
        return numberOfQuantomixPerPlayerLeft.indexOfFirst { it != 0 }
    }

    private fun askPlayer(player: Coach, mutableListOfAttack: List<Attack>?,dead: Boolean = false) {
        if (!dead) {
            val changedCurrentQuantomix =
                doYouWantToChangeTheCurrentQuantomix(player)
            changedCurrentQuantomix.battleStats.newAttack(
                askForAttack(mutableListOfAttack,changedCurrentQuantomix.battleStats.trainer!!)
            )
            changedCurrentQuantomix.battleStats.newTarget(
                askForTarget(changedCurrentQuantomix.battleStats.trainer!!)
            )
        }
    }

    private fun getSelectedAttack(player: Coach, mutableListOfAttack: List<Attack>? = null): Attack {
        return when {
            mutableListOfAttack != null && mutableListOfAttack.isNotEmpty() -> mutableListOfAttack.first()
            else -> getAttackFromUserInput()
        }
    }

    private fun getAttackFromUserInput(): Attack {
        TODO("Extern: die Benutzereingabe der Attacke in der CLI implementieren, prüfen ob die attacke möglich (in Quantomix Attackenliste) steht")
    }

    private fun askForAttack(mutableListOfAttack: List<Attack>?, player: Coach): Attack {

        val selectedAttackFromPlayer = getSelectedAttack(player, mutableListOfAttack)
        // Greift auf das aktive Quantomix des Spielers zu.
        val activeQuantomix = player.quantomixTeam.first() //Todo: welche Quantomix sollen verwendet werden?Das Aktuelle aber wie kommen wir da ran?


        return findAttackByName(activeQuantomix, selectedAttackFromPlayer)
            ?: throw IllegalStateException("Angriff '${selectedAttackFromPlayer.attackName}' nicht gefunden")

        }
    private fun findAttackByName(quantomix: Quantomix, desiredAttack: Attack): Attack? {
        return quantomix.attacks.firstOrNull {
            it.attackName.equals(desiredAttack.attackName, ignoreCase = true)
        }
    }

    private fun askForTarget(player: Coach, availableTargets: List<Quantomix>? = null): Quantomix {
        return when {
            availableTargets != null && availableTargets.isNotEmpty() -> availableTargets.first()
            else -> getTargetFromUserInput()
        }
    }

     fun getTargetFromUserInput(): Quantomix {
        TODO("Extern: die Benutzereingabe des Ziels in der CLI implementieren")
    }




    private fun nextQuantomix(player: Coach): Quantomix? {
        TODO(
            "Diese Funktion fragt, welches Quantomix als nächstes eingesetzt werden soll," +
                    "(dies geschieht wie bei den obigen Funktionen). Ist kein Quantomix mehr übrig" +
                    "soll diese Funktion null zurückgeben. Muss die BattleStats mitsetzen"
        )
    }

    private fun doYouWantToChangeTheCurrentQuantomix(player: Coach): Quantomix {
        TODO("jeder Spieler wird gefragt, ob er mit dem Eingesetzten Quantomix weiterkämpfen möchte")
        //askPlayer(currentQuantomix.battleStats!!.trainer, true) um die BattleStats entsprechend zu setzen
    }
}

//TODO(
//"Diese Funktion soll eine andere Funktion aufrufen, welche den " +
//"Spieler zu dem Angriff befragt und dieses an diese Funktion übergibt. " +
//"Diese Funktion gibt dann die Attacke zurück" )