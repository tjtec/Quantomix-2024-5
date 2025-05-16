package hwr.oop.quantomix.fight

import hwr.oop.quantomix.fight.logic.Battle
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class BattleTests : AnnotationSpec() {
    @Test
    fun `BattleTest with normal Attack`() {
        val type1 = Typ("Feuer")
        val type2 = Typ("Flug")
        val type3 = Typ("Wasser")
        val attack = Attack("tackle", Typ("normal"), 40, 100)
        val attacks = listOf(attack)
        val statsGlurak = Stats(78, 84, 78, 109, 85, 100)
        val statsSchillok = Stats(59, 63, 80, 65, 80, 58)
        val glurak = Quantomix("Glurak", type1, type2, statsGlurak, attacks)
        val schillok = Quantomix("Schillok", type3, null, statsSchillok, attacks)
        glurak.battleStats.nextAttack = attack
        glurak.battleStats.target = schillok
        schillok.battleStats.nextAttack = attack
        schillok.battleStats.target = glurak
        val battle = Battle(mutableListOf(glurak, schillok))
        battle.start(listOf(1.0, 1.0, 1.0, 1.0))
        //Assertions.assertThat(glurak.battleStats.stats.kp).isEqualTo(53)
        Assertions.assertThat(glurak.battleStats.stats.kp).isLessThan(glurak.stats.kp)
        Assertions.assertThat(glurak.stats.kp).isEqualTo(78)
        Assertions.assertThat(schillok.battleStats.stats.kp).isEqualTo(26)
        Assertions.assertThat(schillok.stats.kp).isEqualTo(59)
    }

    @Test
    fun `BattleTest with special Attack Type 2`() {
        val type1 = Typ("Feuer")
        val type2 = Typ("Flug")
        val type3 = Typ("Wasser")
        val attack = Attack("Pflücker", Typ("Flug"), 40, 100)
        val attacks = listOf(attack)
        val statsGlurak = Stats(78, 84, 78, 109, 85, 100)
        val statsSchillok = Stats(59, 63, 80, 65, 80, 58)
        val glurak = Quantomix("Glurak", type1, type2, statsGlurak, attacks)
        val schillok = Quantomix("Schillok", type3, null, statsSchillok, attacks)
        glurak.battleStats.nextAttack = attack
        glurak.battleStats.target = schillok
        schillok.battleStats.nextAttack = attack
        schillok.battleStats.target = glurak
        val battle = Battle(mutableListOf(glurak, schillok))
        battle.start(listOf(4.0, 0.25, 1.0, 1.0))
        Assertions.assertThat(glurak.battleStats.stats.kp).isEqualTo(53)
        Assertions.assertThat(glurak.stats.kp).isEqualTo(78)
        Assertions.assertThat(schillok.battleStats.stats.kp).isEqualTo(16)
        Assertions.assertThat(schillok.stats.kp).isEqualTo(59)
    }

    @Test
    fun `BattleTest not effective`() {
        val type1 = Typ("Feuer")
        val type2 = Typ("Flug")
        val type3 = Typ("Normal")
        val attack = Attack("Test", Typ("Kampf"), 40, 100)
        val attacks = listOf(attack)
        val statsGlurak = Stats(78, 84, 78, 109, 85, 100)
        val statsRattfratz = Stats(55, 81, 60, 50, 70, 97)
        val glurak = Quantomix("Glurak", type1, type2, statsGlurak, attacks)
        val rattfratz = Quantomix("Rattfratz", type3, null, statsRattfratz, attacks)
        glurak.battleStats.nextAttack = attack
        glurak.battleStats.target = rattfratz
        rattfratz.battleStats.nextAttack = attack
        rattfratz.battleStats.target = glurak
        val battle = Battle(mutableListOf(glurak, rattfratz))
        battle.start(listOf(0.0, 0.0, 1.0, 1.0))
        Assertions.assertThat(glurak.battleStats.stats.kp).isEqualTo(46)
        Assertions.assertThat(glurak.stats.kp).isEqualTo(78)
        Assertions.assertThat(rattfratz.battleStats.stats.kp).isEqualTo(55)
        Assertions.assertThat(rattfratz.stats.kp).isEqualTo(55)
    }

    @Test
    fun `BattleTest second Quantomix dead`() {
        val type1 = Typ("Feuer")
        val type2 = Typ("Flug")
        val type3 = Typ("Pflanze")
        val type4 = Typ("Psycho")
        val attack = Attack("Glut", Typ("Feuer"), 70, 100)
        val attacks = listOf(attack)
        val statsGlurak = Stats(78, 60, 58, 60, 65, 80)
        val statsOwei = Stats(84, 40, 50, 60, 50, 40)
        val glurak = Quantomix("Glurak", type1, type2, statsGlurak, attacks)
        val owei = Quantomix("Owei", type3, type4, statsOwei, attacks)
        glurak.battleStats.nextAttack = attack
        glurak.battleStats.target = owei
        owei.battleStats.nextAttack = attack
        owei.battleStats.target = glurak
        val battle = Battle(mutableListOf(glurak, owei))
        battle.start(listOf(2.0, 1.0))
        Assertions.assertThat(glurak.battleStats.stats.kp).isEqualTo(78)
        Assertions.assertThat(glurak.stats.kp).isEqualTo(78)
        Assertions.assertThat(owei.battleStats.stats.kp).isEqualTo(0)
        Assertions.assertThat(owei.stats.kp).isEqualTo(84)
    }

    @Test
    fun `Test not enough number of players`() {
        val statsQuantomix = Stats(78, 64, 58, 80, 65, 80)
        val attack = Attack("Glut", Typ("Feuer"), 30, 100)
        val quantomix = Quantomix("Glurak", Typ("Feuer"), Typ("Flug"), statsQuantomix, listOf(attack))
        quantomix.battleStats.nextAttack = attack
        quantomix.battleStats.target = quantomix
        val battle = Battle(mutableListOf(quantomix))
        Assertions.assertThatThrownBy { battle.start() }.message().isEqualTo("Not enough number of players")
    }

    @Test
    fun `Test right effectivity by no second typ`() {
        val attack = Attack("Direkter Treffer", Typ("Geist"), 100, 100)
        val type = Typ("Normal")
        val statsQuantomix = Stats(100, 100, 100, 100, 100, 100)
        val quantomix1 = Quantomix("With no 2.Typ", type, null, statsQuantomix, listOf(attack))
        val quantomix2 = Quantomix("With no 2.Typ", type, null, statsQuantomix, listOf(attack))
        quantomix1.battleStats.nextAttack = attack
        quantomix1.battleStats.target = quantomix2
        quantomix2.battleStats.nextAttack = attack
        quantomix2.battleStats.target = quantomix1
        val battle = Battle(mutableListOf(quantomix1, quantomix2))
        battle.start()
        Assertions.assertThat(quantomix1.battleStats.stats.kp).isEqualTo(50)
        Assertions.assertThat(quantomix2.battleStats.stats.kp).isEqualTo(50)
    }

    @Test
    fun `Test Attack with damage and the effective the Targets stats are debuffed after attack`() {
        val attack = Attack("Direkter Treffer", Typ("Geist"), 100, 100, false, Stats(0, 10, 5, 15, 8, 20))
        val type = Typ("Normal")
        val statsQuantomix = Stats(100, 100, 100, 100, 100, 100)
        val quantomix1 = Quantomix("With no 2.Typ", type, null, statsQuantomix, listOf(attack))
        val quantomix2 = Quantomix("With no 2.Typ", type, null, statsQuantomix, listOf(attack))
        quantomix1.battleStats.nextAttack = attack
        quantomix1.battleStats.target = quantomix2
        quantomix2.battleStats.nextAttack = attack
        quantomix2.battleStats.target = quantomix1
        val battle = Battle(mutableListOf(quantomix1, quantomix2))
        battle.start()
        Assertions.assertThat(quantomix1.battleStats.stats.kp).isEqualTo(50)
        Assertions.assertThat(quantomix1.battleStats.stats.attack).isEqualTo(quantomix1.stats.attack - 10)
        Assertions.assertThat(quantomix1.battleStats.stats.specialAttack).isEqualTo(quantomix1.stats.specialAttack - 15)
        Assertions.assertThat(quantomix1.battleStats.stats.defense).isEqualTo(quantomix1.stats.defense - 5)
        Assertions.assertThat(quantomix1.battleStats.stats.specialDefense)
            .isEqualTo(quantomix1.stats.specialDefense - 8)
        Assertions.assertThat(quantomix1.battleStats.stats.speed).isEqualTo(quantomix1.stats.speed - 20)
        Assertions.assertThat(quantomix2.battleStats.stats.kp).isEqualTo(50)
        Assertions.assertThat(quantomix2.battleStats.stats.attack).isEqualTo(quantomix2.stats.attack - 10)
        Assertions.assertThat(quantomix2.battleStats.stats.specialAttack).isEqualTo(quantomix2.stats.specialAttack - 15)
        Assertions.assertThat(quantomix2.battleStats.stats.defense).isEqualTo(quantomix2.stats.defense - 5)
        Assertions.assertThat(quantomix2.battleStats.stats.specialDefense)
            .isEqualTo(quantomix2.stats.specialDefense - 8)
        Assertions.assertThat(quantomix2.battleStats.stats.speed).isEqualTo(quantomix2.stats.speed - 20)
    }
}
