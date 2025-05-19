package hwr.oop.quantomix.objects

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.monster.Quantomix
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class QuantomixTest: AnnotationSpec() {
    @Test
    fun `Quantomix test`() {
        val type1= Typ("hello")
        val type2= Typ("world")
        val attack = Attack("attack", Typ("Normal"), 40, 100, mutableListOf())
        val attacks = listOf(attack)
        val stats = Stats(200, 100, 30, 70, 35, 88)
        val quantomix = Quantomix("Test", type1, type2, stats, attacks)
        Assertions.assertThat(quantomix.getQuantomixName()).isEqualTo("Test")
        Assertions.assertThat(quantomix.typ1.name).isEqualTo("hello")
        Assertions.assertThat(quantomix.typ2!!.name).isEqualTo("world")
        Assertions.assertThat(quantomix.stats.kp).isEqualTo(200)
        Assertions.assertThat(quantomix.stats.attack).isEqualTo(100)
        Assertions.assertThat(quantomix.stats.defense).isEqualTo(30)
        Assertions.assertThat(quantomix.stats.specialAttack).isEqualTo(70)
        Assertions.assertThat(quantomix.stats.specialDefense).isEqualTo(35)
        Assertions.assertThat(quantomix.stats.speed).isEqualTo(88)
    }
}