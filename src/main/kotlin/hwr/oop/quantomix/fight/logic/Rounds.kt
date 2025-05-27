package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.Memory.CSVLoad
import hwr.oop.quantomix.Memory.CSVSave
import hwr.oop.quantomix.Memory.Load
import hwr.oop.quantomix.Memory.Save
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

//ToDo: mehr als ein Quantomix implementieren
//ToDo: Exeption schreiben Attacke ist nicht in der Liste möglicher auswählbarer Attacken (vielleicht auch erst ins CLI)

class Rounds(private var trainer1: Coach, private var trainer2: Coach) {
    private val chosenAttacksMap = MutableMap<Quantomix, Attack>()
    private val damageFunction: DamageStrategy = StandardDamageStrategy()
    private val battle: Battle = SimpleBattle()
    private val saveMethode: Save = CSVSave()
    private val loadMethode: Load = CSVLoad()

    private var quantomixAndBattleStatsMap = mutableMapOf<Quantomix, BattleStats>()


    private val activeQuantomixTrainer1 = trainer1.getFirstQuantomix()
    private val activeQuantomixTrainer2 = trainer2.getFirstQuantomix()

    fun choseAttack(
        attackingTrainer: Coach,
        attack: Attack,
    ) {
        val attackingQuantomix = attackingQuantomix(attackingTrainer)
        require(chosenAttacksMap[attackingQuantomix] == null) {
            "Attacking trainer has already chosen an attack"
        }
        require(attackingQuantomix.hasAttack(attack)) {
            "Attacking Quantomix does not have the attack"
        }
        chosenAttacksMap[attackingQuantomix] = attack
        if (chosenAttacksMap.size == 2) {
            simulateBattle()
        }
    }


    private fun attackingQuantomix(attackingTrainer: Coach) =
        if (attackingTrainer == trainer1) activeQuantomixTrainer1 else activeQuantomixTrainer2


    private fun simulateBattle() {
        val quantomixInOrderOfAttack = quantomixBySpeed()

        for (attacker in quantomixInOrderOfAttack) {
            val defender = otherQuantomix(attacker)

            val battleStats = getBattleStatsForCombatants(attacker, defender)

            battle.simpleBattle(
                attacker = battleStats.first,
                attack = chosenAttacksMap.getValue(attacker),
                target = battleStats.second,
                damageStrategie = damageFunction
            )
            saveMethode.save(trainer1, trainer2, quantomixAndBattleStatsMap)
        }
        chosenAttacksMap.clear()
    }

    private fun getBattleStatsForCombatants(attacker: Quantomix, defender: Quantomix): Pair<BattleStats, BattleStats> {
        val attackerStats = quantomixAndBattleStatsMap.getOrPut(attacker) { attacker.newBattleStats() }
        val defenderStats = quantomixAndBattleStatsMap.getOrPut(defender) { defender.newBattleStats() }

        return Pair(attackerStats, defenderStats)
    }

    private fun quantomixBySpeed(): List<Quantomix> {
        val activeQuantomix1 = quantomixAndBattleStatsMap.get(activeQuantomixTrainer1)
        val activeQuantomix2 = quantomixAndBattleStatsMap.get(activeQuantomixTrainer2)
        val listOfBattleStats =
            listOf(activeQuantomix1, activeQuantomix2).sortedByDescending { it.getStats().getSpeed() }
        mutableListOf<Quantomix>()
        //ToDo:Schleife fertigmachen
        for (Int i = 0; i < listOfBattleStats.size; i++){

        }
    }

    private fun otherQuantomix(quantomix: Quantomix) =
        if (quantomix == activeQuantomixTrainer1) activeQuantomixTrainer2 else activeQuantomixTrainer1


//        private fun defendingQuantomix(attacking: Coach) =
//            if (attacking == trainer2) activeQuantomixTrainer2 else activeQuantomixTrainer1

    private fun loadRound() {
        val loadHelper = loadMethode.getHelper()
        quantomixAndBattleStatsMap = loadHelper.quantomixAndBattleStatsMap
        trainer1 = loadHelper.trainer1
        trainer2 = loadHelper.trainer2
    }
}