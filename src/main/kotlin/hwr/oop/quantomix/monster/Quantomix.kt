package hwr.oop.quantomix.monster

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.objects.Typ
import kotlinx.serialization.Serializable

@Serializable
class Quantomix(
  private val quantomixName: String,
  private val typ1: Typ,
  private val typ2: Typ?,
  private val stats: Stats,
  private val attacks: List<Attack>,
) {
  fun hasAttack(attack: Attack): Boolean {
    return attack in attacks
  }

  fun getStats(): Stats {
    return stats
  }

  fun getQuantomixName(): String {
    return quantomixName
  }

  fun getType1(): Typ {
    return typ1
  }

  fun getType2(): Typ? {
    return typ2
  }

  fun newBattleStats(): BattleStats {
    return BattleStats(this, stats.deepCopy())
  }
}

