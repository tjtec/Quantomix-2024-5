package hwr.oop.quantomix.objects

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.monster.Quantomix
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class QuantomixTest : AnnotationSpec() {
    @Test
    fun `Quantomix test`() {
        //val type1= Typ("hello")
        //val type2= Typ("world")
        val attack = Attack("attack", Typ.Normal, 40, 100, specialAttack = false, mutableListOf())
        val attacks = listOf(attack)
        val stats = Stats(200, 100, 30, 70, 35, 88)
        val quantomix = Quantomix("Test", Typ.Pflanze, Typ.Psycho, stats, attacks)
        Assertions.assertThat(quantomix.getQuantomixName()).isEqualTo("Test")
        Assertions.assertThat(quantomix.getType1()).isEqualTo(Typ.Pflanze)
        Assertions.assertThat(quantomix.getType2()).isEqualTo(Typ.Psycho)
        Assertions.assertThat(quantomix.getStats().getKp()).isEqualTo(200)
        Assertions.assertThat(quantomix.getStats().getAttack()).isEqualTo(100)
        Assertions.assertThat(quantomix.getStats().getDefense()).isEqualTo(30)
        Assertions.assertThat(quantomix.getStats().getSpecialAttack()).isEqualTo(70)
        Assertions.assertThat(quantomix.getStats().getSpecialDefense()).isEqualTo(35)
        Assertions.assertThat(quantomix.getStats().getSpeed()).isEqualTo(88)
    }
}