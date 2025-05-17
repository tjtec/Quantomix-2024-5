package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.fight.objects.Status
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class BattleStats(
    var stats: Stats,
    var target: Quantomix? = null,
    var nextAttack: Attack? = null,
    var trainer: Coach? = null,
    var status: Status? = null,
) {
    fun newKp(damage: Int) {
        stats.kp = maxOf(0, stats.kp - damage)
    }

    fun newBattleStats(damage: Stats, puff: Boolean) {
        stats.BuffsDebuffs(damage, puff)
    }

    fun newTarget(target: Quantomix) {
        this.target = target
    }

    fun newAttack(attack: Attack) {
        this.nextAttack = attack
    }

    fun newCoach(coach: Coach) {
        this.trainer = coach
    }
}
