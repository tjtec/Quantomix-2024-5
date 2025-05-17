package hwr.oop.quantomix.fight

import hwr.oop.quantomix.fight.logic.Battle
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Effects
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
        battle.start(listOf(1.0f, 1.0f, 1.0f, 1.0f))
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
        battle.start(listOf(4.0f, 0.25f, 1.0f, 1.0f))
        Assertions.assertThat(glurak.battleStats.stats.kp).isEqualTo(53)
        Assertions.assertThat(glurak.stats.kp).isEqualTo(78)
        Assertions.assertThat(schillok.battleStats.stats.kp).isEqualTo(16)
        Assertions.assertThat(schillok.stats.kp).isEqualTo(59)
    }

    @Test
    fun `BattleTest with other Quantomix and special Attack Type 1`() {
        val type1 = Typ("Feuer")
        val type2 = Typ("Flug")
        val type3 = Typ("Normal")
        val attack = Attack("Pflücker", Typ("Flug"), 40, 100)
        val attacks = listOf(attack)
        val statsGlurak = Stats(78, 84, 78, 109, 85, 100)
        val statsTauboss = Stats(83, 80, 75, 70, 70, 101)
        val glurak = Quantomix("Glurak", type1, type2, statsGlurak, attacks)
        val tauboss = Quantomix("Tauboss", type3, type2, statsTauboss, attacks)
        glurak.battleStats.nextAttack = attack
        glurak.battleStats.target = tauboss
        tauboss.battleStats.nextAttack = attack
        tauboss.battleStats.target = glurak
        val battle = Battle(mutableListOf(glurak, tauboss))
        battle.start(listOf(1.0f, 1.0f, 1.0f, 1.0f))
        Assertions.assertThat(glurak.battleStats.stats.kp).isEqualTo(50)
        Assertions.assertThat(glurak.stats.kp).isEqualTo(78)
        Assertions.assertThat(tauboss.battleStats.stats.kp).isEqualTo(40)
        Assertions.assertThat(tauboss.stats.kp).isEqualTo(83)
    }

    @Test
    fun `BattleTest extrem effectiviness and less effectiviness`() {
        val type1 = Typ("Feuer")
        val type2 = Typ("Flug")
        val type3 = Typ("Pflanze")
        val type4 = Typ("Psycho")
        val attack = Attack("Glut", Typ("Feuer"), 20, 100)
        val attacks = listOf(attack)
        val statsGlurak = Stats(78, 64, 58, 80, 65, 80)
        val statsOwei = Stats(60, 40, 80, 60, 45, 40)
        val glurak = Quantomix("Glurak", type1, type2, statsGlurak, attacks)
        val owei = Quantomix("Owei", type3, type4, statsOwei, attacks)
        glurak.battleStats.nextAttack = attack
        glurak.battleStats.target = owei
        owei.battleStats.nextAttack = attack
        owei.battleStats.target = glurak
        val battle = Battle(mutableListOf(glurak, owei))
        battle.start(listOf(2.0f, 1.0f, 0.5f, 1.0f))
        Assertions.assertThat(glurak.battleStats.stats.kp).isEqualTo(70)
        Assertions.assertThat(glurak.stats.kp).isEqualTo(78)
        Assertions.assertThat(owei.battleStats.stats.kp).isEqualTo(28)
        Assertions.assertThat(owei.stats.kp).isEqualTo(60)
    }

    @Test
    fun `BattleTest extrem effectiviness and less effectiviness with data from table`() {
        val type1 = Typ("Feuer")
        val type2 = Typ("Flug")
        val type3 = Typ("Pflanze")
        val attack = Attack("Glut", Typ("Feuer"), 20, 100)
        val attacks = listOf(attack)
        val statsGlurak = Stats(78, 64, 58, 80, 65, 80)
        val statsOwei = Stats(60, 40, 80, 60, 45, 40)
        val glurak = Quantomix("Glurak", type1, type2, statsGlurak, attacks)
        val owei = Quantomix("Owei", type3, null, statsOwei, attacks)
        glurak.battleStats.nextAttack = attack
        glurak.battleStats.target = owei
        owei.battleStats.nextAttack = attack
        owei.battleStats.target = glurak
        val battle = Battle(mutableListOf(glurak, owei))
        battle.start()
        Assertions.assertThat(glurak.battleStats.stats.kp).isEqualTo(70)
        Assertions.assertThat(glurak.stats.kp).isEqualTo(78)
        Assertions.assertThat(owei.battleStats.stats.kp).isEqualTo(28)
        Assertions.assertThat(owei.stats.kp).isEqualTo(60)
        Assertions.assertThat(battle.start()).isNotEmpty
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
        battle.start(listOf(0.0f, 0.0f, 1.0f, 1.0f))
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
        battle.start(listOf(2.0f, 1.0f))
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
    fun `Test Attack with damage and debuff`() {
        val attack1 =
            Attack("Direkter Treffer", Typ("Geist"), 100, 100)
        val attack2 =
            Attack("Direkter Treffer", Typ("Geist"), 100, 100)
        val type = Typ("Normal")
        val statsQuantomix = Stats(100, 100, 100, 100, 100, 100)
        val quantomix1 = Quantomix("With no 2.Typ", type, null, statsQuantomix, listOf(attack1))
        val quantomix2 = Quantomix("With no 2.Typ", type, null, statsQuantomix, listOf(attack2))
        attack1.effects = mutableListOf(Effects(false, Stats(0, 10, 5, 15, 8, 20), quantomix2))
        attack2.effects = mutableListOf(Effects(false, Stats(0, 10, 5, 15, 8, 20), quantomix1))
        quantomix1.battleStats.nextAttack = attack1
        quantomix1.battleStats.target = quantomix2
        quantomix2.battleStats.nextAttack = attack2
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

    @Test
    fun `Test Debuff without Attackdamage`() {
        val attack1 = Attack("Direkter Treffer", Typ("Geist"), 0, 100)
        val attack2 = Attack("Direkter Treffer", Typ("Geist"), 0, 100)
        val type = Typ("Normal")
        val statsQuantomix = Stats(100, 100, 100, 100, 100, 100)
        val quantomix1 = Quantomix("With no 2.Typ", type, null, statsQuantomix, listOf(attack1))
        val quantomix2 = Quantomix("With no 2.Typ", type, null, statsQuantomix, listOf(attack2))
        attack1.effects = mutableListOf(Effects(false, Stats(0, 10, 5, 15, 8, 20), quantomix2))
        attack2.effects = mutableListOf(Effects(false, Stats(0, 10, 5, 15, 8, 20), quantomix1))
        quantomix1.battleStats.nextAttack = attack1
        quantomix1.battleStats.target = quantomix2
        quantomix2.battleStats.nextAttack = attack2
        quantomix2.battleStats.target = quantomix1
        val battle = Battle(mutableListOf(quantomix1, quantomix2))
        battle.start()
        Assertions.assertThat(quantomix1.battleStats.stats.kp).isEqualTo(100)
        Assertions.assertThat(quantomix1.battleStats.stats.attack).isEqualTo(quantomix1.stats.attack - 10)
        Assertions.assertThat(quantomix1.battleStats.stats.specialAttack).isEqualTo(quantomix1.stats.specialAttack - 15)
        Assertions.assertThat(quantomix1.battleStats.stats.defense).isEqualTo(quantomix1.stats.defense - 5)
        Assertions.assertThat(quantomix1.battleStats.stats.specialDefense)
            .isEqualTo(quantomix1.stats.specialDefense - 8)
        Assertions.assertThat(quantomix1.battleStats.stats.speed).isEqualTo(quantomix1.stats.speed - 20)
        Assertions.assertThat(quantomix2.battleStats.stats.kp).isEqualTo(100)
        Assertions.assertThat(quantomix2.battleStats.stats.attack).isEqualTo(quantomix2.stats.attack - 10)
        Assertions.assertThat(quantomix2.battleStats.stats.specialAttack).isEqualTo(quantomix2.stats.specialAttack - 15)
        Assertions.assertThat(quantomix2.battleStats.stats.defense).isEqualTo(quantomix2.stats.defense - 5)
        Assertions.assertThat(quantomix2.battleStats.stats.specialDefense)
            .isEqualTo(quantomix2.stats.specialDefense - 8)
        Assertions.assertThat(quantomix2.battleStats.stats.speed).isEqualTo(quantomix2.stats.speed - 20)
    }

    @Test
    fun `Test Buff of the attacker`() {
        val attack1 = Attack("Direkter Treffer", Typ("Geist"), 0, 100)
        val attack2 = Attack("Direkter Treffer", Typ("Geist"), 0, 100)
        val type = Typ("Normal")
        val statsQuantomix = Stats(100, 100, 100, 100, 100, 100)
        val quantomix1 = Quantomix("With no 2.Typ", type, null, statsQuantomix, listOf(attack1))
        val quantomix2 = Quantomix("With no 2.Typ", type, null, statsQuantomix, listOf(attack2))
        attack1.effects = mutableListOf(Effects(true, Stats(0, 10, 5, 15, 8, 20), quantomix2))
        attack2.effects = mutableListOf(Effects(true, Stats(0, 10, 5, 15, 8, 20), quantomix1))
        quantomix1.battleStats.nextAttack = attack1
        quantomix1.battleStats.target = quantomix2
        quantomix2.battleStats.nextAttack = attack2
        quantomix2.battleStats.target = quantomix1
        val battle = Battle(mutableListOf(quantomix1, quantomix2))
        battle.start()
        Assertions.assertThat(quantomix1.battleStats.stats.kp).isEqualTo(100)
        Assertions.assertThat(quantomix1.battleStats.stats.attack).isEqualTo(quantomix1.stats.attack + 10)
        Assertions.assertThat(quantomix1.battleStats.stats.specialAttack).isEqualTo(quantomix1.stats.specialAttack + 15)
        Assertions.assertThat(quantomix1.battleStats.stats.defense).isEqualTo(quantomix1.stats.defense + 5)
        Assertions.assertThat(quantomix1.battleStats.stats.specialDefense)
            .isEqualTo(quantomix1.stats.specialDefense + 8)
        Assertions.assertThat(quantomix1.battleStats.stats.speed).isEqualTo(quantomix1.stats.speed + 20)
        Assertions.assertThat(quantomix2.battleStats.stats.kp).isEqualTo(100)
        Assertions.assertThat(quantomix2.battleStats.stats.attack).isEqualTo(quantomix2.stats.attack + 10)
        Assertions.assertThat(quantomix2.battleStats.stats.specialAttack).isEqualTo(quantomix2.stats.specialAttack + 15)
        Assertions.assertThat(quantomix2.battleStats.stats.defense).isEqualTo(quantomix2.stats.defense + 5)
        Assertions.assertThat(quantomix2.battleStats.stats.specialDefense)
            .isEqualTo(quantomix2.stats.specialDefense + 8)
        Assertions.assertThat(quantomix2.battleStats.stats.speed).isEqualTo(quantomix2.stats.speed + 20)
    }

    @Test
    fun `Test Attack with damage and buff`() {
        val attack1 = Attack("Direkter Treffer", Typ("Geist"), 100, 100)
        val attack2 = Attack("Direkter Treffer", Typ("Geist"), 100, 100)
        val type = Typ("Normal")
        val statsQuantomix = Stats(100, 100, 100, 100, 100, 100)
        val quantomix1 = Quantomix("With no 2.Typ", type, null, statsQuantomix, listOf(attack1))
        val quantomix2 = Quantomix("With no 2.Typ", type, null, statsQuantomix, listOf(attack2))
        attack1.effects = mutableListOf(Effects(true, Stats(0, 10, 5, 15, 8, 20), quantomix2))
        attack2.effects = mutableListOf(Effects(true, Stats(0, 10, 5, 15, 8, 20), quantomix1))
        quantomix1.battleStats.nextAttack = attack1
        quantomix1.battleStats.target = quantomix2
        quantomix2.battleStats.nextAttack = attack2
        quantomix2.battleStats.target = quantomix1
        val battle = Battle(mutableListOf(quantomix1, quantomix2))
        battle.start()
        Assertions.assertThat(quantomix1.battleStats.stats.kp).isEqualTo(50)
        Assertions.assertThat(quantomix1.battleStats.stats.attack).isEqualTo(quantomix1.stats.attack + 10)
        Assertions.assertThat(quantomix1.battleStats.stats.specialAttack).isEqualTo(quantomix1.stats.specialAttack + 15)
        Assertions.assertThat(quantomix1.battleStats.stats.defense).isEqualTo(quantomix1.stats.defense + 5)
        Assertions.assertThat(quantomix1.battleStats.stats.specialDefense)
            .isEqualTo(quantomix1.stats.specialDefense + 8)
        Assertions.assertThat(quantomix1.battleStats.stats.speed).isEqualTo(quantomix1.stats.speed + 20)
        Assertions.assertThat(quantomix2.battleStats.stats.kp).isEqualTo(50)
        Assertions.assertThat(quantomix2.battleStats.stats.attack).isEqualTo(quantomix2.stats.attack + 10)
        Assertions.assertThat(quantomix2.battleStats.stats.specialAttack).isEqualTo(quantomix2.stats.specialAttack + 15)
        Assertions.assertThat(quantomix2.battleStats.stats.defense).isEqualTo(quantomix2.stats.defense + 5)
        Assertions.assertThat(quantomix2.battleStats.stats.specialDefense)
            .isEqualTo(quantomix2.stats.specialDefense + 8)
        Assertions.assertThat(quantomix2.battleStats.stats.speed).isEqualTo(quantomix2.stats.speed + 20)
    }

    @Test
    fun `Test Attack and attacker is dead`() {
        val attack1 = Attack("Direkter Treffer", Typ("Geist"), 100, 100)
        val attack2 = Attack("Direkter Treffer", Typ("Geist"), 100, 100)
        val type = Typ("Normal")
        val statsQuantomix1 = Stats(100, 100, 100, 100, 100, 99)
        val statsQuantomix2 = Stats(100, 100, 100, 100, 100, 100)
        val quantomix1 = Quantomix("With no 2.Typ", type, null, statsQuantomix1, listOf(attack1))
        val quantomix2 = Quantomix("With no 2.Typ", type, null, statsQuantomix2, listOf(attack2))
        attack1.effects = mutableListOf(Effects(false, Stats(100, 0, 0, 0, 0, 0), quantomix2))
        attack2.effects = mutableListOf(Effects(false, Stats(100, 0, 0, 0, 0, 0), quantomix1))
        quantomix1.battleStats.nextAttack = attack1
        quantomix1.battleStats.target = quantomix2
        quantomix2.battleStats.nextAttack = attack2
        quantomix2.battleStats.target = quantomix1
        val battle = Battle(mutableListOf(quantomix1, quantomix2))
        battle.start()
        Assertions.assertThat(quantomix1.battleStats.stats.kp).isEqualTo(0)
        Assertions.assertThat(quantomix2.battleStats.stats.kp).isEqualTo(100)
    }

    @Test
    fun `Test with Attack with selfdamage & heal and buff & debuff and no damage`() {
        val type = Typ("Normal")
        val statsQuantomix1 = Stats(100, 100, 100, 100, 100, 99)
        val statsQuantomix2 = Stats(100, 100, 100, 100, 100, 100)
        val attack1 = Attack("Selfdamage & buff", Typ("Geist"), 100, 100)
        val attack2 = Attack("Heal and Debuff", Typ("Geist"), 100, 100)
        val quantomix1 = Quantomix("Heal", type, null, statsQuantomix1, listOf(attack1))
        val quantomix2 = Quantomix("Self damage", type, null, statsQuantomix2, listOf(attack2))
        val effects1 = Effects(true, Stats(25, 0, 10, 0, 20, 0), quantomix1)
        val effects111 = Effects(false, Stats(0, 0, 0, 20, 0, 25), quantomix1)
        val effects22 = Effects(false, Stats(0, 10, 0, 0, 0, 0), quantomix1)
        val effects2 = Effects(false, Stats(25, 0, 10, 0, 0, 0), quantomix2)
        val effects11 = Effects(true, Stats(0, 10, 0, 0, 8, 0), quantomix2)
        val effects222 = Effects(true, Stats(0, 0, 0, 20, 0, 25), quantomix2)
        attack1.effects = mutableListOf(effects1, effects11, effects111)
        attack2.effects = mutableListOf(effects2, effects22, effects222)
        attack2.noDamage = true
        quantomix1.battleStats.nextAttack = attack1
        quantomix1.battleStats.target = quantomix2
        quantomix2.battleStats.nextAttack = attack2
        quantomix2.battleStats.target = quantomix1
        val battle = Battle(mutableListOf(quantomix1, quantomix2))
        battle.start()
        Assertions.assertThat(quantomix1.battleStats.stats.kp).isEqualTo(75)
        Assertions.assertThat(quantomix1.battleStats.stats.attack).isEqualTo(quantomix1.stats.attack - 10)
        Assertions.assertThat(quantomix1.battleStats.stats.specialAttack).isEqualTo(quantomix1.stats.specialAttack - 20)
        Assertions.assertThat(quantomix1.battleStats.stats.defense).isEqualTo(quantomix1.stats.defense + 10)
        Assertions.assertThat(quantomix1.battleStats.stats.specialDefense)
            .isEqualTo(quantomix1.stats.specialDefense + 20)
        Assertions.assertThat(quantomix1.battleStats.stats.speed).isEqualTo(quantomix1.stats.speed - 25)
        Assertions.assertThat(quantomix2.battleStats.stats.kp).isEqualTo(75)
        Assertions.assertThat(quantomix2.battleStats.stats.attack).isEqualTo(quantomix2.stats.attack + 10)
        Assertions.assertThat(quantomix2.battleStats.stats.specialAttack).isEqualTo(quantomix2.stats.specialAttack + 20)
        Assertions.assertThat(quantomix2.battleStats.stats.defense).isEqualTo(quantomix2.stats.defense - 10)
        Assertions.assertThat(quantomix2.battleStats.stats.specialDefense)
            .isEqualTo(quantomix2.stats.specialDefense + 8)
        Assertions.assertThat(quantomix2.battleStats.stats.speed).isEqualTo(quantomix2.stats.speed + 25)

    }

}