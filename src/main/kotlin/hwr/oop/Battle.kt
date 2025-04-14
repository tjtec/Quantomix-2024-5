package hwr.oop


class Battle(private var quantomixA: Quantomix, private var quantomixB: Quantomix) {

    private fun nextAttacker(): Quantomix {
        return if (quantomixA.speed >= quantomixB.speed) {
            quantomixA
        } else {
            quantomixB
        }
    }
    private fun otherAttacker(): Quantomix {
        return if (nextAttacker() == quantomixA)  {
            quantomixB
        } else {
            quantomixA
        }
    }

    private fun formulaAttackForce(attackDamage: Int, attackValue:Int):Int{
        return attackDamage * attackValue / 100 - 15
    }

    private fun attackPower(attack: Attack): Int {
        //ToDo: Attacken ohne Schaden miteinbeziehen?
        return if (attack.type == nextAttacker().typ1 || attack.type == nextAttacker().typ2) {
            formulaAttackForce(attack.damage, nextAttacker().specialAttack)
        } else {
            formulaAttackForce(attack.damage, nextAttacker().attack)
        }
    }

//    fun effectivity(attack: Attack): Int{
//
//    }

    fun newKp(attack: Attack): Boolean{
        // changes the kp value of a quantomix
        val attackPower=attackPower(attack)
        val otherQuantomix = otherAttacker()
        otherQuantomix.kp -= attackPower
        return otherQuantomix.kp == 0
    }
}