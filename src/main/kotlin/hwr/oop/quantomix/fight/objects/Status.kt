package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.fight.logic.BattleStats
import hwr.oop.quantomix.monster.Quantomix

class Status(
    var noDamage: Boolean = false,
    var poisoning: Boolean = false,
    var strongPoisoning: Boolean = false,
    var combustion: Boolean = false,
    var paralysis: Boolean = false,
    var sleep: Boolean = false,
    var confusion: Boolean = false,
    var freeze: Boolean = false,
) {
    val duration: Int = (2..5).random()

    fun stickToTarget(target: BattleStats) {
        if (target.status == null) {
            target.status = this
        }
    }

    fun effectsOfStatus(target: Quantomix, alreadyPassedRounds: Int) {
        if (poisoning) {
            target.battleStats.stats.kp -= (target.stats.kp / 16)
        }
        if (strongPoisoning) {
            target.battleStats.stats.kp += (target.stats.kp / 16) * 2
        }
        if (combustion) {
            target.battleStats.stats.kp -= (target.stats.kp / 8)
        }
        if (paralysis) {
            target.battleStats.nextAttack!!.damageQuote = target.battleStats.nextAttack!!.damageQuote * 2 / 3
            target.battleStats.stats.speed = target.battleStats.stats.speed / 2
        }
        if (sleep || freeze) {
            if (duration >= alreadyPassedRounds) {
                if (target.battleStats.target!!.battleStats.status == null) {
                    target.battleStats.target!!.battleStats.status = Status(true)
                } else {
                    target.battleStats.target!!.battleStats.status!!.noDamage = true
                }
            }
        }
        if (confusion) {
            //ToDo: Ich muss irgendwo festhalten, dass die Attacken in 50% der Fälle nach hinten los gehen, doch wo?
        }
    }
}