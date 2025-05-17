package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class StatsTest : AnnotationSpec() {
    @Test
    fun `stats and battleStats not equals`() {
        val statsQuantomix = Stats(100, 100, 100, 100, 100, 100)
        val type = Typ("Normal")
        val attack = Attack("Direkter Treffer", Typ("Geist"), 100, 100)
        val quantomix1 = Quantomix("Test", type, null, statsQuantomix, listOf(attack))
        Assertions.assertThat(quantomix1.battleStats.stats.kp).isEqualTo(quantomix1.stats.kp)
        quantomix1.battleStats.newKp(20)
        Assertions.assertThat(quantomix1.battleStats.stats.kp).isNotEqualTo(quantomix1.stats.kp)
        Assertions.assertThat(quantomix1.battleStats.stats.kp).isEqualTo(80)
        Assertions.assertThat(quantomix1.stats.kp).isEqualTo(100)
    }
}