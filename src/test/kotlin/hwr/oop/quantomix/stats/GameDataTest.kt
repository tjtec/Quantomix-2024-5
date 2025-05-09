package hwr.oop.quantomix.stats

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.AnnotationSpec.Test
import org.assertj.core.api.Assertions

class GameDataTest : AnnotationSpec() {
    @Test
    fun `getEffektivitaet test`() {
        val returnval = GameData.getEffektivitaet("Normal", "Kampf")
        Assertions.assertThat(returnval).isEqualTo(2.0)
    }

    @Test
    fun `store quantomix test`() {
        val type1= Typ("hello")
        val type2= Typ("world")
        val attack= Attack("attack", Typ("Normal"), 40, 100)
        val attacks = listOf(attack)
        val quantomix = Quantomix("Test", type1, type2, 200, 100, 30, 70, 35, 88, attacks)

        Assertions.assertThat(GameData.storeQuantomix(quantomix)).isEqualTo("Test,hello,world,200,100,30,70,35,88,attack|40|100|Normal,null\n")
    }

    @Test
    fun `store quantomix with multiple attacks test`() {
        val type1= Typ("hello")
        val type2= Typ("world")
        val attack= Attack("attack", Typ("Normal"), 40, 100)
        val attack2= Attack("Attacke2", Typ("Feuer"), 100, 200)
        val attack3= Attack("Bruchrechnen", Typ("Mathe"), 10000, 20000)
        val attacks = listOf(attack, attack2, attack3)
        val quantomix = Quantomix("Test", type1, type2, 200, 100, 30, 70, 35, 88, attacks)
        Assertions.assertThat(GameData.storeQuantomix(quantomix)).isEqualTo("Test,hello,world,200,100,30,70,35,88,attack|40|100|Normal<EOA>Attacke2|100|200|Feuer<EOA>Bruchrechnen|10000|20000|Mathe,null\n")
    }
}