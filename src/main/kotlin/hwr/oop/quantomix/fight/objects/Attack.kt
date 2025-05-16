package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.objects.Typ

class Attack (
    val attackName: String,
    val type: Typ,
    val damage: Int,
    val damageQuote: Int,
    val buff: Boolean? = null,
    val changeStats: Stats? = null,
)