package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class Rounds(val trainer: List<Coach>) {
    fun start(): Coach {
        val listOfQuantomixInBattle = mutableListOf<Quantomix>()
        val numberOfPlayers = trainer.size

        for (currentPlayer: Coach in trainer) {
            val attack = askForAttack(currentPlayer, getSelectedAttackName(currentPlayer))
            val target = askForTarget(currentPlayer)
            val stats = BattleStats(
                currentPlayer.quantomixTeam[0].kp,
                currentPlayer.quantomixTeam[0].attack,
                currentPlayer.quantomixTeam[0].defense,
                currentPlayer.quantomixTeam[0].specialAttack,
                currentPlayer.quantomixTeam[0].specialDefense,
                currentPlayer.quantomixTeam[0].speed,
                target,
                attack,
                currentPlayer
            )
            currentPlayer.quantomixTeam[0].battleStats = stats
            listOfQuantomixInBattle.add(currentPlayer.quantomixTeam[0])
        }
        val battle = Battle(listOfQuantomixInBattle)
        val winner = round(battle, listOfQuantomixInBattle, numberOfPlayers)
        return trainer[winner]
    }

    private fun round(
        battle: Battle,
        listOfPreviusQuantomixInBattle: MutableList<Quantomix>,
        numberOfPlayers: Int
    ): Int {
        val quantomixLeftInBattle = battle.start()
        val numberOfQuantomixPerPlayerLeft = MutableList(numberOfPlayers) { 6 }
        var indexForNumberOfQuantomixPerPlayerLeft = 0
        val quantomixInTheNextRound = mutableListOf<Quantomix>()
        var maximumQuantomixInBattle = listOfPreviusQuantomixInBattle.size
        while (numberOfQuantomixPerPlayerLeft.count { it != 0 } != 1) {
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
                    indexForNumberOfQuantomixPerPlayerLeft += 1
                }
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
                askForAttack(changedCurrentQuantomix.battleStats.trainer!!, getSelectedAttackName(changedCurrentQuantomix.battleStats.trainer!!))
            )
            changedCurrentQuantomix.battleStats.newTarget(
                askForTarget(changedCurrentQuantomix.battleStats.trainer!!)
            )
        }
    }

    // Gibt einen Attackennamen zurück, den jemand (zuvor) ausgewählt hat.
    // Simulation: Wir wählen zufällig einen Namen aus dem aktuellen Quantomix.
    private fun getSelectedAttackName(player: Coach): String {
        // Nehme das aktive Quantomix des Spielers (hier z. B. das erste im Team)
        val activeQuantomix = player.quantomixTeam[0]
        // Wähle zufällig einen Attackennamen aus der Liste
        return activeQuantomix.attacks.random().attackName
    }

    private fun askForAttack(player: Coach, attackName: String): Attack {


        val attackName = getSelectedAttackName(player)
        // Greife auf das aktive Quantomix des Spielers zu.

        val activeQuantomix = player.quantomixTeam[0]

        // Suche in der Liste der Attacken des aktiven Quantomix nach einer Attacke,
        // deren Name mit dem übergebenen attackName übereinstimmt (Groß-/Kleinschreibung wird ignoriert).
        val selectedAttack = activeQuantomix.attacks.find {
            it.attackName.equals(attackName, ignoreCase = true)}

        // Wenn eine passende Attacke gefunden wurde, wird sie zurückgegeben.
        // Andernfalls wird als Fallback die erste Attacke aus der Liste gewählt.
        return selectedAttack ?: activeQuantomix.attacks.first()
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