package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.objects.Coach

// Dieses Interface legt fest, dass jede Implementierung eine Methode haben muss,
// um aus einer Liste von Angriffen eine Attacke auszuwählen.
interface AttackSelector {
    fun selectAttack(player: Coach, availableAttacks: List<Attack>): Attack
}