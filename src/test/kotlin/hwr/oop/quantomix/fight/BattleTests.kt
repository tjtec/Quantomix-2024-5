package hwr.oop.quantomix.fight

import hwr.oop.quantomix.fight.logic.Battle
import hwr.oop.quantomix.fight.logic.BattleStats
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach
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
        val glurak = Quantomix("Glurak", type1, type2, 78, 84, 78, 109, 85, 100, attacks)
        val schillok = Quantomix("Schillok", type3, null, 59, 63, 80, 65, 80, 58, attacks)
        val trainer1 = Coach("trainer1", listOf(glurak, schillok, glurak, schillok, glurak, schillok))
        val trainer2 = Coach("trainer2", listOf(schillok, glurak, glurak, schillok, glurak, schillok))
        val statsGlurak = BattleStats(78, 84, 78, 109, 85, 100, schillok, attack, trainer1)
        val statsSchillok = BattleStats(59, 63, 80, 65, 80, 58, glurak, attack, trainer2)
        glurak.battleStats = statsGlurak
        schillok.battleStats = statsSchillok
        val battle = Battle(mutableListOf(glurak, schillok))
        battle.start(listOf(1.0, 1.0, 1.0, 1.0))
        Assertions.assertThat(glurak.battleStats.battleKp).isEqualTo(53)
        Assertions.assertThat(glurak.kp).isEqualTo(78)
        Assertions.assertThat(schillok.battleStats.battleKp).isEqualTo(26)
        Assertions.assertThat(schillok.kp).isEqualTo(59)
    }

    @Test
    fun `BattleTest with special Attack Type 2`() {
        val type1 = Typ("Feuer")
        val type2 = Typ("Flug")
        val type3 = Typ("Wasser")
        val attack = Attack("Pflücker", Typ("Flug"), 40, 100)
        val attacks = listOf(attack)
        val glurak = Quantomix("Glurak", type1, type2, 78, 84, 78, 109, 85, 100, attacks)
        val schillok = Quantomix("Schillok", type3, null, 59, 63, 80, 65, 80, 58, attacks)
        val trainer1 = Coach("trainer1", listOf(glurak, schillok, glurak, schillok, glurak, schillok))
        val trainer2 = Coach("trainer2", listOf(schillok, glurak, glurak, schillok, glurak, schillok))
        val statsGlurak = BattleStats(78, 84, 78, 109, 85, 100, schillok, attack, trainer1)
        glurak.battleStats = statsGlurak
        val statsSchillok = BattleStats(59, 63, 80, 65, 80, 58, glurak, attack, trainer2)
        schillok.battleStats = statsSchillok
        val battle = Battle(mutableListOf(glurak, schillok))
        battle.start(listOf(4.0, 0.25, 1.0, 1.0))
        Assertions.assertThat(glurak.kp).isEqualTo(78)
        Assertions.assertThat(glurak.battleStats.battleKp).isEqualTo(53)
        Assertions.assertThat(schillok.battleStats.battleKp).isEqualTo(16)
        Assertions.assertThat(schillok.kp).isEqualTo(59)
    }

    @Test
    fun `BattleTest with other Quantomix and special Attack Type 1`() {
        val type1 = Typ("Feuer")
        val type2 = Typ("Flug")
        val type3 = Typ("Normal")
        val attack = Attack("Pflücker", Typ("Flug"), 40, 100)
        val attacks = listOf(attack)
        val glurak = Quantomix("Glurak", type1, type2, 78, 84, 78, 109, 85, 100, attacks)
        val tauboss = Quantomix("Tauboss", type3, type2, 83, 80, 75, 70, 70, 101, attacks)
        val trainer1 = Coach("trainer1", listOf(glurak, tauboss, glurak, tauboss, glurak, tauboss))
        val trainer2 = Coach("trainer2", listOf(tauboss, glurak, glurak, tauboss, glurak, tauboss))
        val statsGlurak = BattleStats(78, 84, 78, 109, 85, 100, tauboss, attack, trainer1)
        glurak.battleStats = statsGlurak
        val statsTauboss = BattleStats(83, 80, 75, 70, 70, 101, glurak, attack, trainer2)
        tauboss.battleStats = statsTauboss
        val battle = Battle(mutableListOf(glurak, tauboss))
        battle.start(listOf(1.0, 1.0, 1.0, 1.0))
        Assertions.assertThat(glurak.kp).isEqualTo(78)
        Assertions.assertThat(glurak.battleStats.battleKp).isEqualTo(50)
        Assertions.assertThat(tauboss.kp).isEqualTo(83)
        Assertions.assertThat(tauboss.battleStats.battleKp).isEqualTo(40)
    }

    @Test
    fun `BattleTest extrem effectiviness and less effectiviness`() {
        val type1 = Typ("Feuer")
        val type2 = Typ("Flug")
        val type3 = Typ("Pflanze")
        val type4 = Typ("Psycho")
        val attack = Attack("Glut", Typ("Feuer"), 20, 100)
        val attacks = listOf(attack)
        val glurak = Quantomix("Glurak", type1, type2, 78, 64, 58, 80, 65, 80, attacks)
        val owei = Quantomix("Owei", type3, type4, 60, 40, 80, 60, 45, 40, attacks)
        val trainer1 = Coach("trainer1", listOf(glurak, owei, glurak, owei, glurak, owei))
        val statsGlurak = BattleStats(78, 84, 78, 109, 85, 100, owei, attack, trainer1)
        glurak.battleStats = statsGlurak
        val trainer2 = Coach("trainer2", listOf(owei, glurak, glurak, owei, glurak, owei))
        val statsOwei = BattleStats(60, 40, 80, 60, 45, 40, glurak, attack, trainer2)
        owei.battleStats = statsOwei
        val battle = Battle(mutableListOf(glurak, owei))
        battle.start(listOf(2.0, 1.0, 0.5, 1.0))
        Assertions.assertThat(glurak.kp).isEqualTo(78)
        Assertions.assertThat(glurak.battleStats.battleKp).isEqualTo(70)
        Assertions.assertThat(owei.kp).isEqualTo(60)
        Assertions.assertThat(owei.battleStats.battleKp).isEqualTo(17)
    }

    @Test
    fun `BattleTest extrem effectiviness and less effectiviness with data from table`() {
        val type1 = Typ("Feuer")
        val type2 = Typ("Flug")
        val type3 = Typ("Pflanze")
        val attack = Attack("Glut", Typ("Feuer"), 20, 100)
        val attacks = listOf(attack)
        val glurak = Quantomix("Glurak", type1, type2, 78, 64, 58, 80, 65, 80, attacks)
        val owei = Quantomix("Owei", type3, null, 60, 40, 80, 60, 45, 40, attacks)
        val trainer1 = Coach("trainer1", listOf(glurak, owei, glurak, owei, glurak, owei))
        val statsGlurak = BattleStats(78, 84, 78, 109, 85, 100, owei, attack, trainer1)
        glurak.battleStats = statsGlurak
        val trainer2 = Coach("trainer2", listOf(owei, glurak, glurak, owei, glurak, owei))
        val statsOwei = BattleStats(60, 40, 80, 60, 45, 40, glurak, attack, trainer2)
        owei.battleStats = statsOwei
        val battle = Battle(mutableListOf(glurak, owei))
        battle.start()
        Assertions.assertThat(glurak.kp).isEqualTo(78)
        Assertions.assertThat(glurak.battleStats.battleKp).isEqualTo(70)
        Assertions.assertThat(owei.kp).isEqualTo(60)
        Assertions.assertThat(owei.battleStats.battleKp).isEqualTo(17)
    }

    @Test
    fun `Batteltest not effectiv`() {
        //ToDo:Umschreiben für BattleTest und nextAttacker bekommt weniger als 2 quantomix
        val type1 = Typ("Feuer")
        val type2 = Typ("Flug")
        val type3 = Typ("Normal")
        val attack = Attack("Test", Typ("Kampf"), 40, 100)
        val attacks = listOf(attack)
        val glurak = Quantomix("Glurak", type1, type2, 78, 84, 78, 109, 85, 100, attacks)
        val rattfratz = Quantomix("Rattfratz", type3, null, 55, 81, 60, 50, 70, 97, attacks)
        val trainer1 = Coach("trainer1", listOf(glurak, rattfratz, glurak, rattfratz, glurak, rattfratz))
        val statsGlurak = BattleStats(78, 84, 78, 109, 85, 100, rattfratz, attack, trainer1)
        glurak.battleStats = statsGlurak
        val trainer2 = Coach("trainer2", listOf(rattfratz, glurak, glurak, rattfratz, glurak, rattfratz))
        val statsRattfratz = BattleStats(55, 81, 60, 50, 70, 97, glurak, attack, trainer2)
        rattfratz.battleStats = statsRattfratz
        val battle = Battle(mutableListOf(glurak, rattfratz))
        battle.start(listOf(0.0, 0.0, 1.0, 1.0))
        Assertions.assertThat(glurak.kp).isEqualTo(78)
        Assertions.assertThat(rattfratz.kp).isEqualTo(55)
        Assertions.assertThat(glurak.battleStats.battleKp).isEqualTo(46)
        Assertions.assertThat(rattfratz.battleStats.battleKp).isEqualTo(55)
    }

    @Test
    fun `BattleTest second Quantomix dead`() {
        val type1 = Typ("Feuer")
        val type2 = Typ("Flug")
        val type3 = Typ("Pflanze")
        val type4 = Typ("Psycho")
        val attack = Attack("Glut", Typ("Feuer"), 70, 100)
        val attacks = listOf(attack)
        val glurak = Quantomix("Glurak", type1, type2, 78, 60, 58, 60, 65, 80, attacks)
        val owei = Quantomix("Owei", type3, type4, 84, 40, 50, 60, 50, 40, attacks)
        val trainer1 = Coach("trainer1", listOf(glurak, owei, glurak, owei, glurak, owei))
        glurak.battleStats.newTarget(owei)
        glurak.battleStats.newAttack(attack)
        glurak.battleStats.newCoach(trainer1)
        val trainer2 = Coach("trainer2", listOf(owei, glurak, glurak, owei, glurak, owei))
        owei.battleStats.newTarget(glurak)
        owei.battleStats.newCoach(trainer2)
        owei.battleStats.newAttack(attack)
        val battle = Battle(mutableListOf(glurak, owei))
        battle.start(listOf(2.0, 1.0))
        Assertions.assertThat(glurak.kp).isEqualTo(78)
        Assertions.assertThat(glurak.battleStats.battleKp).isEqualTo(78)
        Assertions.assertThat(owei.kp).isEqualTo(84)
        Assertions.assertThat(owei.battleStats.battleKp).isEqualTo(0)
        Assertions.assertThat(battle.start(listOf(2.0, 0.5))).isNotEmpty
    }

    @Test
    fun `Test not enough number of players`() {
        val quantomix = Quantomix(
            "Glurak",
            Typ("Feuer"),
            Typ("Flug"),
            78,
            64,
            58,
            80,
            65,
            80,
            listOf(Attack("Glut", Typ("Feuer"), 30, 100))
        )
        val trainer1 = Coach("Test", listOf(quantomix, quantomix, quantomix, quantomix, quantomix, quantomix))
        val battleStatsQuantomix =
            BattleStats(78, 84, 78, 109, 85, 100, quantomix, Attack("Glut", Typ("Feuer"), 30, 100), trainer1)
        quantomix.battleStats = battleStatsQuantomix
        val battle = Battle(mutableListOf(quantomix))
        Assertions.assertThatThrownBy { battle.start() }.message().isEqualTo("Not enough number of players")
    }

    @Test
    fun `Test right effectivity by no second typ`() {
        val attack = Attack("Direkter Treffer", Typ("Geist"), 100, 100)
        val type = Typ("Normal")
        val quantomix1 = Quantomix(
            "With no 2.Typ",
            type,
            null,
            100,
            100,
            100,
            100,
            100,
            100,
            listOf(attack)
        )
        val quantomix2 = Quantomix(
            "With no 2.Typ",
            type,
            null,
            100,
            100,
            100,
            100,
            100,
            100,
            listOf(attack)
        )
        val trainer1 = Coach(
            "Trainer1",
            listOf(quantomix1, quantomix2, quantomix1, quantomix2, quantomix1, quantomix2)
        )
        val trainer2 = Coach(
            "Trainer2",
            listOf(quantomix2, quantomix1, quantomix2, quantomix1, quantomix2, quantomix1)
        )
        quantomix1.battleStats = BattleStats(
            100,
            50,
            100,
            100,
            100,
            100,
            quantomix2,
            attack,
            trainer1
        )
        quantomix2.battleStats = BattleStats(
            100,
            50,
            100,
            100,
            100,
            100,
            quantomix1,
            attack,
            trainer2
        )

        val battle = Battle(mutableListOf(quantomix1, quantomix2))
        battle.start()
        Assertions.assertThat(quantomix1.battleStats.battleKp).isEqualTo(50)
        Assertions.assertThat(quantomix2.battleStats.battleKp).isEqualTo(50)
    }
}