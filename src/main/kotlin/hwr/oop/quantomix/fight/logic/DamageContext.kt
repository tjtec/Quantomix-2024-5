package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.fight.objects.StatusHelper

data class DamageContext(
  val attacker: BattleStats,
  val target: BattleStats,
  val attack: Attack,
) {
  val statusEffect: StatusHelper = if (attack.hasStatus()) {
    target.changesAccordingToStatus()
  } else {
    StatusHelper()
  }
  val isSelfHit: Boolean = statusEffect.selfHit()
}
