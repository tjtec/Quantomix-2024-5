package hwr.oop

class Battle(var quantomixA: Quantomix, var quantomixB: Quantomix) {

    fun nextAttacker(): Quantomix {
        if ((quantomixA.speed*5/quantomixA.speed) >= (quantomixB.speed*5/quantomixB.speed)) {
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