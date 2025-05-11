package hwr.oop.quantomix.cli

import hwr.oop.quantomix.fight.logic.Rounds
import hwr.oop.quantomix.fight.logic.AttackSelector
import hwr.oop.quantomix.objects.Coach

fun main() {
    // Erstelle deine Trainer. Hier hängt es von deiner Implementierung ab.
    // Zum Beispiel:
    val trainer: List<Coach> = createTrainerList()  // Implementiere createTrainerList(), um Trainer zu erzeugen.

    // Erstelle eine Instanz deines CLI AttackSelectors.
    val attackSelector: AttackSelector = CliAttackSelector()

    // Erstelle eine Rounds-Instanz und starte das Spiel.
    val rounds = Rounds(trainer, attackSelector)
    val winner = rounds.start()

    println("Der Gewinner ist: ${winner.coachName}")
}

// Dummy-Funktion als Beispiel. Implementiere das nach deinen Anforderungen.
fun createTrainerList(): List<Coach> {
    // Erstelle Trainer-Objekte, fülle die Quantomix-Teams etc.
    return listOf()  // Hier deine konkrete Logik einfügen.
}