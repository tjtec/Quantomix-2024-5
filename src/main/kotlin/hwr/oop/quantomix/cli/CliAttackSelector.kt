package hwr.oop.quantomix.cli

import hwr.oop.quantomix.fight.logic.AttackSelector
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.objects.Coach
import java.util.Scanner

class CliAttackSelector : AttackSelector {
    private val scanner = Scanner(System.`in`)

    override fun selectAttack(player: Coach, availableAttacks: List<Attack>): Attack {
        println("Wähle einen Angriff für ${player.coachName}:")
        availableAttacks.forEachIndexed { index, attack ->
            println("${index + 1}. ${attack.attackName} (Typ: ${attack.type}, Schaden: ${attack.damage})")
        }

        println("Bitte gib die Nummer des gewünschten Angriffs ein:")

        while (true) {
            val input = scanner.nextLine()
            val choice = input.toIntOrNull()
            if (choice != null && choice in 1..availableAttacks.size) {
                return availableAttacks[choice - 1]
            } else {
                println("Ungültige Eingabe. Bitte wähle eine Zahl zwischen 1 und ${availableAttacks.size}.")
            }
        }
    }
}