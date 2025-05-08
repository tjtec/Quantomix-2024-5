package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class BattleStats(
    var battleKp: Int,
    var battleAttack: Int,
    var battleDefense: Int,
    var battleSpecialAttack: Int,
    var battleSpecialDefense: Int,
    var battleSpeed: Int,
    var target: Quantomix? = null,
    var nextAttack: Attack? = null,
    var trainer: Coach? = null,
) {
    fun newKp(damage: Int) {
        if (damage >= battleKp) {
            this.battleKp = 0
        } else {
            this.battleKp -= damage
        }
    }

    fun newBattleAttack(damage: Int) {
        this.battleAttack -= damage
    }

    fun newBattleDefense(damage: Int) {
        this.battleDefense -= damage
    }

    fun newBattleSpecialAttack(damage: Int) {
        this.battleSpecialAttack -= damage
    }

    fun newBattleSpecialDefense(damage: Int) {
        this.battleSpecialDefense -= damage
    }

    fun newBattleSpeed(damage: Int) {
        this.battleSpeed -= damage
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