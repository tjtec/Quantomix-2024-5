package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class RoundsWith2Quantomix(private var trainer1: Coach, private var trainer2: Coach) : Rounds {
    private val chosenAttacksMap = mutableMapOf<Quantomix, Attack>()
    private val damageFunction: DamageStrategy = StandardDamageStrategy()
    private val battle = SimpleBattle()
    private val chosenTargetsMap = mutableMapOf<Quantomix, Quantomix>()

    private var quantomixAndBattleStatsMap = mutableMapOf<Quantomix, BattleStats>()

    private val teamOfActiveQuantomixTrainer1: List<Quantomix> =
        trainer1.getActiveQuantomix(numberOfQuantomixInRound = 2)
    private val teamOfActiveQuantomixTrainer2: List<Quantomix> =
        trainer2.getActiveQuantomix(numberOfQuantomixInRound = 2)

    override fun chooseAttack(
        attackingQuantomix: Quantomix,
        attack: Attack,
    ) {
        require(chosenAttacksMap[attackingQuantomix] == null) {
            "Attacking trainer has already chosen an attack"
        }
        require(attackingQuantomix.hasAttack(attack)) {
            "Attacking Quantomix does not have the attack"
        }
        chosenAttacksMap[attackingQuantomix] = attack
        triggerBattleIfReady()
    }

    fun chooseTarget(forAttacker: Quantomix, target: Quantomix) {
        require(teamOfActiveQuantomixTrainer1.contains(forAttacker) ||
                teamOfActiveQuantomixTrainer2.contains(forAttacker)) { "Der angreifende Quantomix ist in diesem Kampf nicht aktiv." }

        // Bestimme das gegnerische Team
        val attackingTeam = if (teamOfActiveQuantomixTrainer1.contains(forAttacker))
            teamOfActiveQuantomixTrainer1
        else
            teamOfActiveQuantomixTrainer2

        val opponentTeam = if (attackingTeam == teamOfActiveQuantomixTrainer1)
            teamOfActiveQuantomixTrainer2
        else
            teamOfActiveQuantomixTrainer1

        require(opponentTeam.contains(target)) {
            "Das gewählte Ziel gehört nicht zur gegnerischen Mannschaft."
        }
        require(chosenTargetsMap[forAttacker] == null) {
            "Für diesen Quantomix wurde bereits ein Ziel ausgewählt."
        }
        chosenTargetsMap[forAttacker] = target

        // Falls alle Quantomixe (Attacke und Ziel) ausgewählt haben, starte den Kampf.
        triggerBattleIfReady()
    }

    private fun triggerBattleIfReady() {
        val totalActiveUnits = teamOfActiveQuantomixTrainer1.size + teamOfActiveQuantomixTrainer2.size
        if (chosenAttacksMap.size == totalActiveUnits && chosenTargetsMap.size == totalActiveUnits) {
            simulateBattle()
        }
    }

    private fun simulateBattle() {
        // Hier wird nicht mehr automatisch der Gegner ermittelt (via otherQuantomix),
        // sondern das im Vorfeld mit chooseTarget ausgewählte Ziel aus der Map genutzt.
        val battleStatsInOrderOfAttack = quantomixBySpeed()
        for ((attacker, attackerStats) in battleStatsInOrderOfAttack) {
            val defender = chosenTargetsMap[attacker]
                ?: throw IllegalStateException("Kein Ziel für den Angreifer $attacker gewählt.")
            val battleStats = getBattleStatsForCombatants(attacker, defender)
            if (attackerStats.isAlive()) {
                battle.simpleBattle(
                    aktiveQuantomixBattleStats = attackerStats,
                    attack = chosenAttacksMap.getValue(attacker),
                    target = battleStats.second,
                    attackStrategy = damageFunction
                )
            }
        }
        // Nach der Runde alle Auswahl-Maps zurücksetzen.
        chosenAttacksMap.clear()
        chosenTargetsMap.clear()
    }

    private fun getBattleStatsForCombatants(
        attacker: Quantomix,
        defender: Quantomix,
    ): Pair<BattleStats, BattleStats> {
        val attackerStats =
            quantomixAndBattleStatsMap.getOrPut(attacker) { attacker.newBattleStats() }
        val defenderStats =
            quantomixAndBattleStatsMap.getOrPut(defender) { defender.newBattleStats() }
        return Pair(attackerStats, defenderStats)
    }

    private fun quantomixBySpeed(): Map<Quantomix, BattleStats> {
        val allQuantomixes: List<Quantomix> = teamOfActiveQuantomixTrainer1 + teamOfActiveQuantomixTrainer2
        allQuantomixes.forEach { qm ->
            quantomixAndBattleStatsMap.getOrPut(qm) { qm.newBattleStats() }
        }
        return quantomixAndBattleStatsMap.entries
            .sortedByDescending { it.value.getStats().getSpeed() }
            .associate { it.key to it.value }
    }
}
