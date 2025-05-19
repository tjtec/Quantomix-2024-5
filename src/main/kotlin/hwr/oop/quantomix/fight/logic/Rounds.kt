package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class Rounds(val trainer: List<Coach>) {
    var mutableListOfAttack: List<Attack>? = null
    var mutableListOfTargets: List<Quantomix>? = null
    var mutableListOfQuantomix: List<Quantomix>? = null
    fun start(
        numberOfQuantomixPerTrainer: Int = 1,
        mutableListOfAttack1: List<Attack>? = null,
        mutableListOfTarget1: List<Quantomix>? = null,
        mutableListOfQuantomix1: List<Quantomix>? = null,
    ): Coach {
        mutableListOfAttack = mutableListOfAttack1
        mutableListOfTargets = mutableListOfTarget1
        mutableListOfQuantomix = mutableListOfQuantomix1
        val listOfQuantomixInBattle = mutableListOf<Quantomix>()
        val numberOfPlayers = trainer.size
        for (currentPlayer: Coach in trainer) {
            for (i in 0 until numberOfQuantomixPerTrainer) {
                requireNotNull(currentPlayer.quantomixTeam[i].battleStats).newAttack(askForAttack(currentPlayer))
                requireNotNull(currentPlayer.quantomixTeam[i].battleStats).newTarget(askForTarget(currentPlayer))
                requireNotNull(currentPlayer.quantomixTeam[i].battleStats).newCoach(currentPlayer)
                listOfQuantomixInBattle.add(currentPlayer.quantomixTeam[i])

            }
        }
        val battle = Battle(listOfQuantomixInBattle)
        val winner = round(
            battle, listOfQuantomixInBattle,
            numberOfPlayers, numberOfQuantomixPerTrainer
        )
        return trainer[winner]
    }

    private fun round(
        battle: Battle,
        previousQuantomix: MutableList<Quantomix>,
        numberOfPlayers: Int,
        numberOfPlayersPerQuantomix: Int
    ): Int {
        val remainingPerPlayer = MutableList(numberOfPlayers) { 6 }
        var maxQuantomixInBattle = previousQuantomix.size

        while (remainingPerPlayer.count { it != 0 } != 1) {
            val activeQuantomix = battle.start()
            val nextRound: List<Quantomix>
            if (activeQuantomix.size < maxQuantomixInBattle) {
                val result = processRoundWithReplacement(
                    previousQuantomix,
                    activeQuantomix,
                    remainingPerPlayer,
                    numberOfPlayersPerQuantomix,
                    maxQuantomixInBattle
                )
                nextRound = result.first
                maxQuantomixInBattle = result.second
            } else {
                // ... ansonsten alle Quantomix übernehmen und erneut einen neuen Battle starten.
                nextRound = processRoundWithoutReplacement(previousQuantomix)
                Battle(nextRound as MutableList<Quantomix>).start()
            }

            // Aktualisiere die Liste für die nächste Runde.
            previousQuantomix.clear()
            previousQuantomix.addAll(nextRound)
        }
        // Gib den Index des Spielers zurück, der noch Quantomix übrig hat.
        return remainingPerPlayer.indexOfFirst { it != 0 }
    }

    private fun processRoundWithReplacement(
        previousQuantomix: List<Quantomix>,
        activeQuantomix: List<Quantomix>,
        remainingPerPlayer: MutableList<Int>,
        numberOfPlayersPerQuantomix: Int,
        maxQuantomixInBattle: Int
    ): Pair<List<Quantomix>, Int> {
        val nextRound = mutableListOf<Quantomix>()
        var updatedMaxQuantomix = maxQuantomixInBattle
        var playerIndex = 0
        var moveCount = 0

        for (quantomix in previousQuantomix) {
            if (!activeQuantomix.contains(quantomix)) {
                val replacement = nextQuantomix(quantomix.battleStats.trainer!!)
                if (replacement != null) {
                    nextRound.add(replacement)
                    remainingPerPlayer[playerIndex]--
                } else {
                    updatedMaxQuantomix--
                }
            } else {
                askPlayer(quantomix.battleStats.trainer!!)
                nextRound.add(quantomix)
            }

            moveCount++
            if (moveCount == numberOfPlayersPerQuantomix) {
                playerIndex++
                moveCount = 0
            }
        }
        return Pair(nextRound, updatedMaxQuantomix)
    }

    private fun processRoundWithoutReplacement(previousQuantomix: List<Quantomix>): List<Quantomix> {
        val nextRound = mutableListOf<Quantomix>()
        for (quantomix in previousQuantomix) {
            askPlayer(quantomix.battleStats.trainer!!)
            nextRound.add(quantomix)
        }
        return nextRound
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

    private fun getSelectedAttack(player: Coach): Attack {
        if (mutableListOfAttack != null && mutableListOfAttack!!.isNotEmpty()) {
            val attack = mutableListOfAttack!!.first()
            mutableListOfAttack = mutableListOfAttack!!.drop(1)
            return attack
        }
        // Hier wird ein Platzhalter aufgerufen, der später durch eine tatsächliche Benutzereingabe ersetzt werden soll.
        else {
            return mutableListOfAttack!!.first()//ToDo: getAttackFromUserInput() (sehe unten)
        }
    }

    private fun askForTarget(player: Coach): Quantomix {
        if (mutableListOfTargets != null && mutableListOfTargets!!.isNotEmpty()) {
            val target = mutableListOfTargets!!.first()
            mutableListOfTargets = mutableListOfTargets?.drop(1)
            return target
        }
        // Hier wird ein Platzhalter aufgerufen, der später durch eine tatsächliche Benutzereingabe ersetzt werden soll.
        else {
            return mutableListOfTargets!!.first()//ToDo:getTargetFromUserInput()
        }
    }

    /*private fun getAttackFromUserInput(): Attack {
        //ToDo:("Extern: die Benutzereingabe der Attacke in der CLI implementieren, prüfen ob die attacke möglich (in Quantomix Attackenliste) steht")
    }*/

    /*fun getTargetFromUserInput(): Quantomix {
        //ToDo: ("Extern: die Benutzereingabe des Ziels in der CLI implementieren")
    }*/

    private fun askForAttack(player: Coach): Attack {

        val selectedAttackFromPlayer = getSelectedAttack(player)
        // Greift auf das aktive Quantomix des Spielers zu.
        val activeQuantomix =
            player.quantomixTeam.first() //Todo: welche Quantomix sollen verwendet werden?Das Aktuelle aber wie kommen wir da ran?


        return findAttackByName(activeQuantomix, selectedAttackFromPlayer)
            ?: throw IllegalStateException("Angriff '${selectedAttackFromPlayer.attackName}' nicht gefunden")

    }

    private fun findAttackByName(quantomix: Quantomix, desiredAttack: Attack): Attack? {
        return quantomix.attacks.firstOrNull {
            it.attackName.equals(desiredAttack.attackName, ignoreCase = true)
        }
    }


    private fun nextQuantomix(player: Coach): Quantomix? {
        // Hier werden alle Quantomix gefiltert, die noch einsatzfähig sind.
        val availableQuantomix = player.quantomixTeam.filter { it.kp > 0 }

        if (availableQuantomix.isNotEmpty()) {
            // Hier wird das Pokémon mit den höchsten verbleibenden KP gewählt.
            if (!mutableListOfQuantomix.isNullOrEmpty()) {
                val nextQuantomix = mutableListOfQuantomix!!.first()
                nextQuantomix.battleStats.target = askForTarget(nextQuantomix.battleStats.trainer!!)
                nextQuantomix.battleStats.nextAttack = askForAttack(nextQuantomix.battleStats.trainer!!)
                mutableListOfQuantomix = mutableListOfQuantomix!!.drop(1)
                return nextQuantomix
            } else {
                return mutableListOfTargets!!.maxByOrNull { it.kp }//ToDo: getNextQuantomixFromUserInput(player)
            }
        } else {
            return null
        }
    }


    /*fun getNextQuantomixFromUserInput(player: Coach): Quantomix? {
        //ToDo:("Extern: Implementiere die Benutzerinteraktion (z. B. über die CLI), um das nächste Quantomix auszuwählen")
    }*/


    private fun doYouWantToChangeTheCurrentQuantomix(player: Coach): Quantomix {
        val active = player.quantomixTeam.first()


        // Falls der aktive Quantomix bereits ausgeschieden ist, muss gewechselt werden.
        // Andernfalls wird eine Entscheidung durch den User getroffen – aktuell als Platzhalter durch eine separate Funktion.
        val shouldSwitch = when {
            active.kp <= 0 -> true
            else -> true //Todo: getSwitchDecisionFromUserInput(player)
        }

        return if (!shouldSwitch) {
            active
        } else {
            // ermittelt alle Alternativen, die nicht das aktive Quantomix sind und noch KP > 0 haben.
            val alternatives = player.quantomixTeam.filter { it != active && it.kp > 0 }
            // Wenn keine Alternative verfügbar ist, bleibt der aktive Quantomix erhalten.
            val selected = if (alternatives.isEmpty()) {
                active
            } else {
                active
                //Todo: getNextQuantomixFromUserInput(player) ?:
            }
            selected.battleStats.trainer = player
            selected
        }
    }

    /*private fun getSwitchDecisionFromUserInput(player: Coach): Boolean {
        //ToDo:("Platzhalter: Hier soll später die Entscheidung (true/false) per Benutzereingabe abgefragt werden")
    }*/

}





