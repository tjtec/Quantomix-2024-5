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
        return if (nextAttacker() == quantomixA) {
            quantomixB
        } else {
            quantomixA
        }
    }

    private fun formulaAttackForce(attackDamage: Int, attackValue: Int): Int {
        return attackDamage * attackValue / 100 - 15
    }

    private fun attackPower(attack: Attack, nextAttacker: Quantomix): Int {
        //ToDo: Attacken ohne Schaden miteinbeziehen?
        var result = 0.0
        if (attack.type == nextAttacker().typ1.name || (nextAttacker().typ2 != null && attack.type == nextAttacker().typ2!!.name)) {
            result = formulaAttackForce(attack.damage!!, nextAttacker.specialAttack) * effectivity(attack)
        } else {
            result = formulaAttackForce(attack.damage!!, nextAttacker.attack) * effectivity(attack)
        }
        return result.toInt()
    }

    private fun effectivity(attack: Attack): Double {
        val effDB = GameData().effDB;
        print("${otherAttacker().quantomixName}")
        val effictivity1 = when (DBHandler().getData(
            effDB,
            Effektivitaet().Klassen.get(otherAttacker().typ1.name)!!,
            Effektivitaet().Klassen.get(attack.type)!!
        )[0]) {
            "+" -> 2.0
            "-" -> 1.0
            "0" -> 0.5
            "x" -> 0.0
            else -> throw IllegalArgumentException("Ungültiges Symbol")
        }
        var effictivity2 = 0.0
        if (otherAttacker().typ2 != null) {
            effictivity2 = when ((DBHandler().getData(

                effDB,
                Effektivitaet().Klassen.get(otherAttacker().typ2!!.name)!!,
                Effektivitaet().Klassen.get(attack.type)!!
            )[0])) {
                "+" -> 2.0
                "-" -> 1.0
                "0" -> 0.5
                "x" -> 0.0
                else -> throw IllegalArgumentException("Ungültiges Symbol")
            }
        }
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
        val attackPower = attackPower(attack, nextAttacker())
        val otherQuantomix = otherAttacker()
        if (attackPower > otherQuantomix.kp) {
            otherQuantomix.kp = 0
        } else {
            otherQuantomix.kp -= attackPower
        }
        return otherQuantomix.kp == 0
    }
}
