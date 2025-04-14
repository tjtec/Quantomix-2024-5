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
            formulaAttackForce(attack.damage, nextAttacker().specialAttack)*effectivity(attack)
        } else {
            formulaAttackForce(attack.damage, nextAttacker().attack)*effectivity(attack)
        }
    }

    fun effectivity(attack: Attack): Int{
        var otherAttacker = otherAttacker()
        var effictivity1= getEffectivity(otherAttacker().typ1,attack.type) //braucht einen Int
        // 2 für sehr effektiv, 1 für effektiv, 0,5 für nicht effektiv und 0 für wirkungslos
        var effictivity2 = if (otherAttacker().typ2 != ""){
            getEffectivity(otherAttacker().typ2,attack.type)
        }
        else {0}
        if (effictivity1*effictivity2==1){
            return effictivity1*effictivity2
        }
        else if (effictivity1>effictivity2){
            return effictivity1
        }
        else{
            return effictivity2
        }
    }

    fun newKp(attack: Attack): Boolean{
        // changes the kp value of a quantomix
        val attackPower=attackPower(attack)
        val otherQuantomix = otherAttacker()
        otherQuantomix.kp -= attackPower
        return otherQuantomix.kp == 0
    }
}