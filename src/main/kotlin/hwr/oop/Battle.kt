package hwr.oop

class Battle(var quantomixA: Quantomix, var quantomixB: Quantomix) {

    fun nextAttacker (): Quantomix{
        if (quantomixA.speed >= quantomixB.speed){
            return quantomixA;
        }
        else {
            return quantomixB;
        }
    }

    fun attackPower (attack:Attack): Int {
        //ToDo: überlege eine bessere Berechnung
        //ToDo: Attacken ohne Schaden miteinbeziehen?
        var attackPower: Int=0
        if (nextAttacker()==quantomixA){
            var otherQuantomix: Quantomix = quantomixA
        }
        else {
            var otherQuantomix: Quantomix = quantomixB
        }
        if (attack.type == nextAttacker().typ1){
            val otherQuantomix
            attackPower=attack.damage*nextAttacker().specialAttack/otherQuantomix.specialDefense
        }
        else if (attack.type == nextAttacker().typ2){
            attackPower=attack.damage*nextAttacker().specialAttack/otherQuantomix.specialDefense
        }
        else {
            attackPower=attack.damage*nextAttacker().attack/otherQuantomix.defense
        }
        return attackPower
    }
}