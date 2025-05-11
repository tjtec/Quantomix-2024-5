package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class Rounds(val trainer: List<Coach>, private val attackSelector: AttackSelector) {
    fun start(): Coach {
        val listOfQuantomixInBattle = mutableListOf<Quantomix>()
        val numberOfPlayers = trainer.size
        for (currentPlayer: Coach in trainer) {
            val attack = askForAttack(currentPlayer)
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
    private fun askForAttack(player: Coach): Attack {
        // Hier rufen wir den AttackSelector auf und brauchen keine zusätzliche Hilfsmethode
        return attackSelector.selectAttack(player, player.quantomixTeam[0].attacks)
    }
    private fun askForTarget(player: Coach): /* hier den Typ einfügen */ Any {
        // Analog: Implementiere hier die Auswahl des Targets, entweder durch eine ähnliche Strategie wie bei der Attacke
        TODO("Implementiere die Zielauswahl")
    }



    private fun round(
        battle: Battle,
        listOfPreviusQuantomixInBattle: MutableList<Quantomix>,
        numberOfPlayers: Int
    ): Int {
        val QuantomixLeftInBattle = battle.start()
        val numberOfQuantomixPerPlayerLeft = MutableList(numberOfPlayers) { 6 }
        var indexForNumberOfQuantomixPerPlayerLeft = 0
        val QuantomixInTheNextRound = mutableListOf<Quantomix>()
        var maximumQuantomixInBattle = listOfPreviusQuantomixInBattle.size
        while (numberOfQuantomixPerPlayerLeft.count { it != 0 } != 1) {
            if (QuantomixLeftInBattle.size < maximumQuantomixInBattle) {
                for (currentQuantomix in listOfPreviusQuantomixInBattle) {
                    if (!QuantomixLeftInBattle.contains(currentQuantomix)) {
                        val next = nextQuantomix(currentQuantomix.battleStats.trainer!!)
                        if (next != null) {
                            QuantomixInTheNextRound.add(next)
                            numberOfQuantomixPerPlayerLeft[indexForNumberOfQuantomixPerPlayerLeft] -= 1
                        } else {
                            maximumQuantomixInBattle -= 1
                        }
                    } else {
                        askPlayer(currentQuantomix.battleStats.trainer!!)
                        QuantomixInTheNextRound.add(currentQuantomix)
                    }
                    indexForNumberOfQuantomixPerPlayerLeft += 1
                }
            } else {
                for (currentQuantomix in listOfPreviusQuantomixInBattle) {
                    askPlayer(currentQuantomix.battleStats.trainer!!)
                    QuantomixInTheNextRound.add(currentQuantomix)
                }
                val nextBattle = Battle(QuantomixInTheNextRound)
                nextBattle.start()
            }
        }
        return numberOfQuantomixPerPlayerLeft.indexOfFirst { it != 0 }
    }

    private fun askPlayer(player: Coach, dead: Boolean = false) {
        if (!dead) {
            val changedCurrentQuantomix =
                DoYouWantToChangeTheCurrentQuantomix(player)
            changedCurrentQuantomix.battleStats.newAttack(
                askForAttack(changedCurrentQuantomix.battleStats.trainer!!)
            )
            changedCurrentQuantomix.battleStats.newTarget(
                askForTarget(changedCurrentQuantomix.battleStats.trainer!!)
            )
        }
    }

    //private fun askForAttack(player: Coach): Attack {
        //TODO(
            "Diese Funktion soll eine andere Funktion aufrufen, welche den " +
                    "Spieler zu dem Angriff befragt und dieses an diese Funktion übergibt. " +
                    "Diese Funktion gibt dann die Attacke zurück"
        //)
        // Diese Funktion soll letztlich eine Attacke liefern, nachdem der Spieler seine Auswahl getroffen hat.
            //val activeQuantomix = player.quantomixTeam[0]
            //return chooseAttackFromPlayer(activeQuantomix.attacks, player)
        //}

        // Diese Funktion soll den Spieler fragen, welche Attacke er einsetzen möchte. Hier wird die erste Attacke zurückgegeben
        //private fun chooseAttackFromPlayer(attacks: List<Attack>, player: Coach): Attack {
            //return attacks.random()
        //}

    //private fun askForTarget(player: Coach): Quantomix {
        //TODO(
            "Diese Funktion soll eine andere Funktion aufrufen, welche den " +
                    "Spieler zum Ziel der Attacke befragt und dieses an diese Funktion übergibt. " +
                    "Diese Funktion gibt dann das Ziel der Attacke  zurück"
        //)

    //}

    private fun nextQuantomix(player: Coach): Quantomix? {
        TODO(
            "Diese Funktion fragt, welches Quantomix als nächstes eingesetzt werden soll," +
                    "(dies geschieht wie bei den obrigen Funktionen). Ist kein Quantomix mehr übrig" +
                    "soll diese Funktion null zurückgeben. Muss die BattleStats mitsetzen"
        )
    }

    private fun DoYouWantToChangeTheCurrentQuantomix(player: Coach): Quantomix {
        TODO("jeder Spieler wird gefragt, ob er mit dem Eingesetzten Quantomix weiterkämpfen möchte")
        //askPlayer(currentQuantomix.battleStats!!.trainer, true) um die BattleStats entsprechent zu setzen
    }
}

