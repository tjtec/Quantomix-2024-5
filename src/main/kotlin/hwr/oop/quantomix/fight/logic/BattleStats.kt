package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class BattleStats(
    var battleKp: Int,
    var BattleAttack: Int,
    var BattleDefense: Int,
    var BattleSpecialAttack: Int,
    var BattleSpecialDefense: Int,
    var BattleSpeed: Int,
    var target: Quantomix,
    var nextAttack: Attack,
    val trainer: Coach,
) {
    fun newKp(damage: Int) {
        if (damage > battleKp) {
            this.battleKp = 0
        } else {
            this.battleKp -= damage
        }
    }

    fun newBattleAttack(damage: Int) {
        this.BattleAttack -= damage
    }

    fun newBattleDefense(damage: Int) {
        this.BattleDefense -= damage
    }

    fun newBattleSpecialAttack(damage: Int) {
        this.BattleSpecialAttack -= damage
    }

    fun newBattleSpecialDefense(damage: Int) {
        this.BattleSpecialDefense -= damage
    }

    fun newBattleSpeed(damage: Int) {
        this.BattleSpeed -= damage
    }

    fun newTarget(target: Quantomix) {
        this.target = target
    }

    fun newAttack(attack: Attack) {
        this.nextAttack = attack
    }
}