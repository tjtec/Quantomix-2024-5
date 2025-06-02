package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats

class SimpleBattle {
  fun simpleBattle(
    aktiveQuantomixBattleStats: BattleStats,
    attack: Attack,
    target: BattleStats,
    attackStrategy: DamageStrategy,
  ): Boolean {
    if (attack.hits()) {
      val damage = attackStrategy.damageFunction(
        attacker = aktiveQuantomixBattleStats,
        target = target,
        attack = attack
      )
      target.takeDamage(damage)
      attack.changeStatsAndStatus(aktiveQuantomixBattleStats, target)
      target.changesAccordingToStatus()
    }
    return target.isAlive()
  }
}