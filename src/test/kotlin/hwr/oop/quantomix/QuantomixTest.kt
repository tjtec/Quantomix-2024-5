package hwr.oop.quantomix

import hwr.oop.Attack
import hwr.oop.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class QuantomixTest: AnnotationSpec() {
    @Test
    fun `Quantomix test`() {
        val type1= Typ("hello")
        val type2= Typ("world")
        val attack= Attack("attack", "Normal", 40, 100)
        val attacks = listOf(attack)
        val quantomix = Quantomix("Test", type1, type2, 200, 100, 30, 70, 35, 88, attacks)
        assertThat(quantomix.quantomixName).isEqualTo("Test")
        assertThat(quantomix.typ1.name).isEqualTo("hello")
        assertThat(quantomix.typ2!!.name).isEqualTo("world")
        assertThat(quantomix.kp).isEqualTo(200)
        assertThat(quantomix.attack).isEqualTo(100)
        assertThat(quantomix.defense).isEqualTo(30)
        assertThat(quantomix.specialAttack).isEqualTo(70)
        assertThat(quantomix.specialDefense).isEqualTo(35)
        assertThat(quantomix.speed).isEqualTo(88)
    }
}