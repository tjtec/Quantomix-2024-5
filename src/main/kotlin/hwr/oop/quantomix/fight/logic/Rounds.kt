package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class Rounds(val trainer: List<Coach>) {
    var mutableListOfAttack: List<Attack>? = null
    var mutableListOfQuantomix: List<Quantomix>? = null
    fun start(
        numberOfQuantomixPerTrainer: Int = 1,
        mutableListOfAttack1: List<Attack>? = null,
        mutableListOfQuantomix1: List<Quantomix>? = null
    ): Coach {
        mutableListOfAttack = mutableListOfAttack1
        mutableListOfQuantomix = mutableListOfQuantomix1
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
        val winner = round(
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
        if (mutableListOfQuantomix != null && mutableListOfQuantomix!!.isNotEmpty()) {
            val target = mutableListOfQuantomix!!.first()
            mutableListOfQuantomix = mutableListOfQuantomix!!.drop(1)
            return target
        }
        // Hier wird ein Platzhalter aufgerufen, der später durch eine tatsächliche Benutzereingabe ersetzt werden soll.
        else {
            return mutableListOfQuantomix!!.first()//ToDo:getTargetFromUserInput()
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

        return if (availableQuantomix.isNotEmpty()) {
            // Hier wird das Pokémon mit den höchsten verbleibenden KP gewählt.
            mutableListOfQuantomix!!.maxByOrNull { it.kp }
        } else {
            mutableListOfQuantomix!!.maxByOrNull { it.kp }//ToDo: getNextQuantomixFromUserInput(player)
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





