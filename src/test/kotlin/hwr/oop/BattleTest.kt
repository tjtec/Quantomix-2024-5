package hwr.oop

import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class BattleTest : AnnotationSpec() {
    @Test
    fun `Battletest with normal Attack`() {
        val type1= Typ("Feuer")
        val type2= Typ("Flug")
        val type3= Typ("Wasser")
        val attack:Attack=Attack("tackle", "normal", 40, 100)
        val attacks= listOf(attack)
        val glurak = Quantomix("Glurak", type1, type2, 78, 84, 78, 109, 85, 100, attacks)
        val schillok = Quantomix("Schillok", type3, null, 59, 63, 80, 65, 80, 58, attacks)
        val battle = Battle(glurak, schillok)
        battle.newKp(attack)
        assertThat(glurak.kp).isEqualTo(78)
        assertThat(schillok.kp).isEqualTo(41)
    }
    @Test
    fun `Batteltest with special Attack Type 2`() {
        val type1= Typ("Feuer")
        val type2= Typ("Flug")
        val type3= Typ("Wasser")
        val attack:Attack=Attack("Pflücker", "Flug", 40, 100)
        val attacks= listOf(attack)
        val glurak=Quantomix("Glurak", type1, type2, 78,84,78,109,85,100, attacks)
        val schillok=Quantomix("Schillok", type3, null, 59,63,80,65,80,58, attacks)
        val battle = Battle(glurak, schillok)
        battle.newKp(attack)
        assertThat(glurak.kp).isEqualTo(78)
        assertThat(schillok.kp).isEqualTo(31)
    }
    @Test
    fun `Batteltest with other Quantomix and special Attack Type 1`() {
        val type1= Typ("Feuer")
        val type2= Typ("Flug")
        val type3= Typ("Normal")
        val attack:Attack=Attack("Glut", "Feuer", 40, 100)
        val attacks= listOf(attack)
        val glurak=Quantomix("Glurak", type1, type2, 78,84,78,109,85,100, attacks)
        val tauboss=Quantomix("Tauboss", type3, type2, 83,80,75,70,70,101, attacks)
        val battle = Battle(glurak, tauboss)
        battle.newKp(attack)
        assertThat(glurak.kp).isEqualTo(61)
        assertThat(tauboss.kp).isEqualTo(83)
    }
    @Test
    fun `Batteltest extrem effectiviness`() {
        val type1= Typ("Feuer")
        val type2= Typ("Flug")
        val type3= Typ("Pflanze")
        val type4= Typ("Psycho")
        val attack:Attack=Attack("Glut", "Feuer", 40, 100)
        val attacks= listOf(attack)
        val glurak=Quantomix("Glurak", type1, type2, 78,64,58,80,65,80, attacks)
        val owei=Quantomix("Owei", type3, type4, 60,40,80,60,45,40, attacks)
        val battle = Battle(glurak, owei)
        battle.newKp(attack)
        assertThat(glurak.kp).isEqualTo(78)
        assertThat(owei.kp).isEqualTo(32)
    }
    @Test
    fun `Batteltest less effectiviness`() {
        val type1= Typ("Feuer")
        val type2= Typ("Flug")
        val type3= Typ("Pflanze")
        val type4= Typ("Psycho")
        val attack:Attack=Attack("Tackle", "Normal", 40, 100)
        val attacks= listOf(attack)
        val glurak=Quantomix("Glurak", type1, type2, 78,84,78,109,85,100, attacks)
        val owei=Quantomix("Owei", type3, type4, 60,40,80,60,45,40, attacks)
        val battle = Battle(glurak, owei)
        battle.newKp(attack)
        assertThat(glurak.kp).isEqualTo(78)
        assertThat(owei.kp).isEqualTo(51)
    }
    @Test
    fun `Batteltest effectiviness`() {
        val type1= Typ("Feuer")
        val type2= Typ("Flug")
        val type3= Typ("Pflanze")
        val type4= Typ("Psycho")
        val attack:Attack=Attack("Test", "Gift", 40, 100)
        val attacks= listOf(attack)
        val glurak=Quantomix("Glurak", type1, type2, 78,84,78,109,85,100, attacks)
        val owei=Quantomix("Owei", type3, type4, 60,40,80,60,45,40, attacks)
        val battle = Battle(glurak, owei)
        battle.newKp(attack)
        assertThat(glurak.kp).isEqualTo(78)
        assertThat(owei.kp).isEqualTo(42)
        }
    @Test
    fun `Batteltest not effectiv`() {
        val type1= Typ("Feuer")
        val type2= Typ("Flug")
        val type3= Typ("Normal")
        val attack:Attack=Attack("Test", "Kampf", 40, 100)
        val attacks= listOf(attack)
        val glurak=Quantomix("Glurak", type1, type2, 78,84,78,109,85,100, attacks)
        val rattfratz=Quantomix("Rattfratz", type3, null, 55,81,60,50,70,97, attacks)
        val battle = Battle(glurak, rattfratz)
        battle.newKp(attack)
        assertThat(glurak.kp).isEqualTo(78)
        assertThat(rattfratz.kp).isEqualTo(30)
    }
}