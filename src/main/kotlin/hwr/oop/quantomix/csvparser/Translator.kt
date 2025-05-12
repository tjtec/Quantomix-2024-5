package hwr.oop.quantomix.csvparser

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach
import hwr.oop.quantomix.objects.Typ

interface Translator {
    fun translateToAttack(
        name: String,
        type: String,
        damage: String,
        damageQuote: String,
    ): Attack {
        return Attack(name, Typ(type), damage.toInt(), damageQuote.toInt())
    }

    fun translateToQuantomix(
        name: String,
        typ1: String,
        typ2: String,
        kp: String,
        attack: String,
        defense: String,
        specialAttack: String,
        specialDefense: String,
        speed: String,
        attacks: List<Attack>
    ): Quantomix {
        return Quantomix(
            name,
            Typ(typ1),
            Typ(typ2),
            kp.toInt(),
            attack.toInt(),
            defense.toInt(),
            specialAttack.toInt(),
            specialDefense.toInt(),
            speed.toInt(),
            attacks
        )
    }

    fun translateToCoach(name: String, quantomix: List<Quantomix>): Coach {
        return Coach(name, quantomix)
    }
}