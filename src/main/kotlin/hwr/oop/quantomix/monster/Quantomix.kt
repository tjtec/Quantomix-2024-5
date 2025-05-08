package hwr.oop.quantomix.monster

import hwr.oop.quantomix.fight.logic.BattleStats
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.objects.Coach
import hwr.oop.quantomix.objects.Typ

class Quantomix(
    val quantomixName: String,
    val typ1: Typ,
    val typ2: Typ?,
    var kp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
    val attacks: List<Attack>,
    var battleStats: BattleStats = BattleStats(
        kp, attack, defense, specialAttack, specialDefense, speed,
        Quantomix(
            "Lückenfüller",
            Typ("Lücke"),
            null,
            0,
            0,
            0,
            0,
            0,
            0,
            listOf(Attack("Lückenfüller", Typ("Lücke"), null, null))
        ), attacks[0],
        Coach(
            "Lückenfüller",
            listOf(
                Quantomix(
                    "Lückenfüller",
                    Typ("Lücke"),
                    null,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    listOf(Attack("Lückenfüller", Typ("Lücke"), null, null))
                )
            )
        )
    )
)