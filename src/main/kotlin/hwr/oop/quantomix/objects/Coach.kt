package hwr.oop.quantomix.objects

import hwr.oop.quantomix.monster.Quantomix

class Coach (
    val coachName: String,
    val quantomixTeam: List<Quantomix>
    ) {
    init {
        require(quantomixTeam.isNotEmpty()) { "Ein Coach muss mindestens einen Quantomix besitzen." }
        require(quantomixTeam.size <= 6) { "Ein Coach darf höchstens 6 Quantomix besitzen, aber es wurden ${quantomixTeam.size} übergeben." }
    }

    fun getFirstQuantomix(): Quantomix {
        return quantomixTeam[0]
    }

    fun findAliveQuantomix(currentQuantomix: Quantomix, activeTrainer: Coach): Quantomix {
        if (currentQuantomix.getStats().getKp() > 0) {
            return currentQuantomix
        }
        val aliveQuantomix = activeTrainer.quantomixTeam.filter { it.getStats().getKp() > 0 }
        return aliveQuantomix.maxByOrNull { it.getStats().getKp() }
            ?: throw IllegalStateException("Kein aktiver Quantomix mehr verfügbar für Trainer ${activeTrainer.coachName}")
    }
}
