package hwr.oop


class Battle(private var quantomixA: Quantomix, private var quantomixB: Quantomix) {

    fun nextAttacker(): Quantomix {
        return if (quantomixA.speed >= quantomixB.speed) {
            quantomixA
        } else {
            quantomixB
        }
    }
    fun otherAttacker(): Quantomix {
        return if (nextAttacker() == quantomixA)  {
            quantomixB
        } else {
            quantomixA
        }
    }

    private fun formulaAttackForce(attackDamage: Int, attackValue:Int):Int{
        return attackDamage * attackValue / 100 - 15
    }

    private fun attackPower(attack: Attack, nextAttacker:Quantomix): Int {
        //ToDo: Attacken ohne Schaden miteinbeziehen?
        return if (attack.type == nextAttacker().typ1 || attack.type == nextAttacker().typ2) {
            formulaAttackForce(attack.damage, nextAttacker.specialAttack)
            //*effectivity(attack)
        } else {
            formulaAttackForce(attack.damage, nextAttacker.attack)
            //*effectivity(attack)
        }
    }

//    private fun effectivity(attack: Attack): Int{
//        val effictivity1= getEffectivity(otherAttacker().typ1,attack.type) //braucht einen Int
//        // 2 für sehr effektiv, 1 für effektiv, 0,5 für nicht effektiv und 0 für wirkungslos
//        val effictivity2 = if (otherAttacker().typ2 != ""){
//            getEffectivity(otherAttacker().typ2,attack.type)
//        }
//        else {0}
//        return if (effictivity1*effictivity2==1){
//            effictivity1*effictivity2
//        } else if (effictivity1>effictivity2){
//            effictivity1
//        } else{
//            effictivity2
//        }
//    }

    fun newKp(attack: Attack,attacker:Quantomix): Boolean{
        // changes the kp value of a quantomix
        val attackPower=attackPower(attack, attacker)
        val otherQuantomix = otherAttacker()
        otherQuantomix.kp -= attackPower
        return otherQuantomix.kp == 0
    }
}