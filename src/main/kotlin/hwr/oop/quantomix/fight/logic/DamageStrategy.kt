package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("type")
sealed interface DamageStrategy {
  fun damageFunction(
    attacker: BattleStats,
    target: BattleStats,
    attack: Attack,
  ): Int
}
