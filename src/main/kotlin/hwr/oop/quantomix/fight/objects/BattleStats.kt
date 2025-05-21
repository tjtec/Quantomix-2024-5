package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.monster.Quantomix

class BattleStats(
    private val quantomix: Quantomix,
    private val stats: Stats,
    private var status: Status? = null,
) {
    fun newKp(damage: Int) {
        this.stats.newKp(damage)
    }

    fun getStats(): Stats {
        return this.stats
    }

    fun changeStatus(newStatus: Status) {
        if (this.status == null) {
            this.status = newStatus
        }
    }

    fun changeStats(): Stats {
        return this.stats
    }

    fun getQuantomix(): Quantomix {
        return quantomix
    }

}