package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.monster.Quantomix

class BattleStats(
    private val quantomix: Quantomix,
    private val stats: Stats,
    private var status: Status? = null,
) {
    private var currentStatusHelper: StatusHelper = StatusHelper()
    fun newKp(damage: Int) {
        this.stats.newKp(damage)
    }

    fun getStats(): Stats {
        return this.stats
    }

    fun changeStatus(newStatus: Status): Boolean {
        if (this.status == null) {
            this.status = newStatus
            currentStatusHelper = StatusHelper()
            return true
        }
        return false
    }

    fun changesAccordingToStatus(): StatusHelper {
        if (this.status != null) {
            currentStatusHelper = this.status!!.calculateStatusEffect(previous = currentStatusHelper, battleStats = this)
            this.stats.newKp(currentStatusHelper.summand)
            return currentStatusHelper
        } else {
            return currentStatusHelper
        }
    }


    fun getQuantomix(): Quantomix {
        return quantomix
    }
    fun isAlive():Boolean{
        return when(this.stats.getKp()){
            0->false
            else -> true
        }
    }
}