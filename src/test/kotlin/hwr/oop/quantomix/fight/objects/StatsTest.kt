package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat

class StatsTest : AnnotationSpec() {
  @Test
  fun `stats and battleStats not equals`() {
    val statsQuantomix = Stats(100, 100, 100, 100, 100, 100)
    val type = Typ.Normal
    val attack = Attack(
      "Direkter Treffer",
      Typ.Geist,
      100,
      100,
      effects = mutableListOf(),
      specialAttack = false
    )
    val quantomix1 =
      Quantomix("Test", type, null, statsQuantomix, listOf(attack))
    val battleStatsQuantomix1 = quantomix1.newBattleStats()
    assertThat(battleStatsQuantomix1.getStats().getKp())
      .isEqualTo(quantomix1.getStats().getKp())
    battleStatsQuantomix1.takeDamage(30)
    assertThat(battleStatsQuantomix1.getStats().getKp())
      .isNotEqualTo(quantomix1.getStats().getKp())
    assertThat(battleStatsQuantomix1.getStats().getKp())
      .isEqualTo(70)
    assertThat(quantomix1.getStats().getKp()).isEqualTo(100)
    assertThat(battleStatsQuantomix1.getQuantomix())
      .isEqualTo(quantomix1)
  }
}