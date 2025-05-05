package hwr.oop.quantomix.objects

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class QuantomixTest: AnnotationSpec() {
    @Test
    fun `Quantomix test`() {
        val type1= Typ("hello")
        val type2= Typ("world")
        val attack= Attack("attack", "Normal", 40, 100)
        val attacks = listOf(attack)
        val quantomix = Quantomix("Test", type1, type2, 200, 100, 30, 70, 35, 88, attacks)
        Assertions.assertThat(quantomix.quantomixName).isEqualTo("Test")
        Assertions.assertThat(quantomix.typ1.name).isEqualTo("hello")
        Assertions.assertThat(quantomix.typ2!!.name).isEqualTo("world")
        Assertions.assertThat(quantomix.kp).isEqualTo(200)
        Assertions.assertThat(quantomix.attack).isEqualTo(100)
        Assertions.assertThat(quantomix.defense).isEqualTo(30)
        Assertions.assertThat(quantomix.specialAttack).isEqualTo(70)
        Assertions.assertThat(quantomix.specialDefense).isEqualTo(35)
        Assertions.assertThat(quantomix.speed).isEqualTo(88)
    }
}