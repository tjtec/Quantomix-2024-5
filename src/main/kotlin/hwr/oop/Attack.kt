package hwr.oop

class Attack (
    val attackName: String,
    val type: String,
    val damage: Int,
    val damageQuote: Int,
    val effectiveness: String //sehr effektiv(x2); normal(x1); nicht effektiv(x0.5); garnicht effektiv (normale Attacken)
// ToDo: effectiveness: Vielleicht sinnvoller erst im Kampf zu betrachten
)