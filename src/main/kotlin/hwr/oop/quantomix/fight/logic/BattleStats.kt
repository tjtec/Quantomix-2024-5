package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.fight.objects.Status
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class BattleStats(
    private val quantomix: Quantomix,
    private val stats: Stats,
    private var target: Quantomix? = null,
    private var nextAttack: Attack? = null,
    private var trainer: Coach? = null,
    private var status: Status? = null,
) {
    fun newKp(damage: Int) {
        this.stats.newKp(damage)
    }

    fun getStats(): Stats {
        return this.stats
    }

    fun getTarget(): Quantomix? {
        return target
    }

    fun changeStatus(newStatus: Status) {
        if (this.status == null) {
            this.status = newStatus
        }
    }

    fun changeStats(): Stats {
        return this.stats
    }

    fun newTarget(target: Quantomix) {
        this.target = target
    }

    fun newAttack(attack: Attack) {
        this.nextAttack = attack
    }

}
