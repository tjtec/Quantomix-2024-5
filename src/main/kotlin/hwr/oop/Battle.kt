package hwr.oop

import java.io.File

class Battle(var quantomixA: Quantomix, var quantomixB: Quantomix) {

    fun nextAttacker(): Quantomix {
        if (quantomixA.speed >= quantomixB.speed) {
            return quantomixA;
        } else {
            return quantomixB;
        }
    }

    fun attackPower(attack: Attack): Int {
        //ToDo: überlege eine bessere Berechnung
        //ToDo: Attacken ohne Schaden miteinbeziehen?
        var attackPower: Int = 0
        val otherQuantomix = if (nextAttacker() == quantomixA) {
            quantomixA
        } else {
            quantomixB
        }
        if (attack.type == nextAttacker().typ1) {
            attackPower = attack.damage * nextAttacker().specialAttack / otherQuantomix.specialDefense
        } else if (attack.type == nextAttacker().typ2) {
            attackPower = attack.damage * nextAttacker().specialAttack / otherQuantomix.specialDefense
        } else {
            attackPower = attack.damage * nextAttacker().attack / otherQuantomix.defense
        }
        return attackPower
    }

//    fun effectivity(attack: Attack): Int{
//
//    }

    fun newKp(attack: Attack): Boolean{
        // changes the kp value of a quantomix
        val attackPower=attackPower(attack)
        val otherQuantomix = if (nextAttacker() == quantomixA) {
            quantomixA
        } else {
            quantomixB
        }
        otherQuantomix.inputKp -= attackPower
        return otherQuantomix.inputKp == 0
    }
}

fun main() {
    val monsterDB = File("src/main/kotlin/hwr/oop/resources/test.csv");
    val monster1:Quantomix=DBHandler().getMonsterbyNameObject(monsterDB, "Glurak")
    val monster2:Quantomix=DBHandler().getMonsterbyNameObject(monsterDB, "Schillok")
    val battle = Battle(monster1, monster2)
    val attack:Attack=Attack("tackle", "normal", 40, 100, "normal")
    battle.newKp(attack)

}