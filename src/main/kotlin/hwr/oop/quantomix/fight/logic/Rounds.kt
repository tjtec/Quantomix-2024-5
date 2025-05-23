package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class Rounds(private val trainer1: Coach, private val trainer2: Coach) {
    private val chosenAttacksMap = mutableMapOf<Quantomix, Attack>()
    private val damageCalculator: DamageStrategy = Damage()
}