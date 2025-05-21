package hwr.oop.quantomix.csvparser

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach
import hwr.oop.quantomix.objects.Typ

class Translator {
    fun translateToAttack(
        name: String,
        type: String,
        damage: String,
        damageQuote: String,
    ): Attack {
        val getTyp = Typ.Normal
        return Attack(name, getTyp.getFromString(type), damage.toInt(), damageQuote.toInt(), effects = mutableListOf())
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
        val type2 = when (typ2) {
            "" -> null
            else -> Typ.Normal.getFromString(typ2)
        }
        val stats = Stats(
            kp.toInt(),
            attack.toInt(),
            defense.toInt(),
            specialAttack.toInt(),
            specialDefense.toInt(),
            speed.toInt()
        )
        return Quantomix(
            name,
            Typ.Normal.getFromString(typ1),
            type2,
            stats,
            attacks
        )
    }

    fun translateToCoach(name: String, quantomix: List<Quantomix>): Coach {
        return Coach(name, quantomix)
    }
}