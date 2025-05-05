package hwr.oop.quantomix.monster

import hwr.oop.quantomix.fight.logic.BattleStats
import hwr.oop.quantomix.fight.objects.Attack
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
    var battleStats: BattleStats? = null
)