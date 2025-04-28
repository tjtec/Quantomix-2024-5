package hwr.oop

import hwr.oop.quantomix.objects.Typ

class Quantomix(
    val quantomixName: String,
    val typ1: Typ,
    val typ2: Typ,
    var kp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
    val attacks: List<Attack>
)