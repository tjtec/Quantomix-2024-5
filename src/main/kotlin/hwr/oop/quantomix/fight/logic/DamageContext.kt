package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.fight.objects.StatusHelper

data class DamageContext(
  val attacker: BattleStats,
  val target: BattleStats,
  val attack: Attack,
  val selfHitMultiplier:Int = 0
) {
  val statusEffect: StatusHelper = if (this.attack.hasStatus()) {
    StatusHelper(multiplicator = this.attacker.changesAccordingToStatus().multiplicator,
    summand = this.target.changesAccordingToStatus().summand)
  } else {
    StatusHelper()
  }
  val isSelfHit: Boolean = statusEffect.selfHit(this.selfHitMultiplier)
}
