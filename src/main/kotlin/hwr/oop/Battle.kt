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
        return if (nextAttacker() == quantomixA) {
            quantomixB
        } else {
            quantomixA
        }
    }

    private fun formulaAttackForce(attackDamage: Int, attackValue: Int): Int {
        return attackDamage * attackValue / 100 - 15
    }

    private fun attackPower(attack: Attack): Int {
        //ToDo: Attacken ohne Schaden miteinbeziehen?
        if (attack.type == nextAttacker().typ1 || attack.type == nextAttacker().typ2) {
            val returnInt = formulaAttackForce(attack.damage, nextAttacker().specialAttack) * effectivity(attack)
            return returnInt.toInt()
        } else {
            val returnInt = formulaAttackForce(attack.damage, nextAttacker().attack) * effectivity(attack)
            return returnInt.toInt()
        }
    }

    private fun effectivity(attack: Attack): Double {
        val effDB = GameData().effDB;
        val effictivity1 = when (DBHandler().getData(
            effDB,
            Effektivitaet().Klassen.get(otherAttacker().typ1)!!,
            Effektivitaet().Klassen.get(attack.type)!!
        )[0]) {
            "+" -> 2.0
            "-" -> 1.0
            "0" -> 0.5
            "x" -> 0.0
            else -> throw IllegalArgumentException("Ungültiges Symbol")
        }
        val effictivity2 = when (DBHandler().getData(
            effDB,
            Effektivitaet().Klassen.get(otherAttacker().typ2)!!,
            Effektivitaet().Klassen.get(attack.type)!!
        )[0]) {
            "+" -> 2.0
            "-" -> 1.0
            "0" -> 0.5
            "x" -> 0.0
            else -> throw IllegalArgumentException("Ungültiges Symbol")
        }

        //val effictivity1= getEffectivity(otherAttacker().typ1,attack.type) //braucht einen Int
        // 2 für sehr effektiv, 1 für effektiv, 0,5 für nicht effektiv und 0 für wirkungslos
        //val effictivity2 = if (otherAttacker().typ2 != ""){
        //    getEffectivity(otherAttacker().typ2,attack.type)

        return if (effictivity1 * effictivity2 == 1.0) {
            effictivity1 * effictivity2
        } else if (effictivity1 > effictivity2) {
            effictivity1
        } else {
            effictivity2
        }
    }

    fun newKp(attack: Attack): Boolean {
        // changes the kp value of a quantomix
        val attackPower = attackPower(attack)
        val otherQuantomix = otherAttacker()
        otherQuantomix.kp -= attackPower
        return otherQuantomix.kp == 0
    }
}