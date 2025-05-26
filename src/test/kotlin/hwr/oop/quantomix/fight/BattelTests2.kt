package hwr.oop.quantomix.fight

import hwr.oop.quantomix.fight.logic.Battle
import hwr.oop.quantomix.fight.logic.DamageStrategy
import hwr.oop.quantomix.fight.logic.SimpleBattle
import hwr.oop.quantomix.fight.logic.StandardDamageStrategy
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Effects
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class BattelTests2 : AnnotationSpec() {
    @BeforeEach
    fun quantomix1(): Quantomix {
        val quantomix1 = Quantomix(
            quantomixName = "Quantomix1",
            typ1 = Typ.Normal,
            typ2 = null,
            stats = Stats(
                kp = 100,
                attack = 100,
                defense = 100,
                specialAttack = 100,
                specialDefense = 100,
                speed = 100
            ),
            attacks = listOf(
            )
        )
        return quantomix1
    }

    @BeforeEach
    fun quantomix2(): Quantomix {
        val quantomix2 = Quantomix(
            quantomixName = "Quantomix1",
            typ1 = Typ.Normal,
            typ2 = null,
            stats = Stats(
                kp = 100,
                attack = 100,
                defense = 100,
                specialAttack = 100,
                specialDefense = 100,
                speed = 100
            ),
            attacks = listOf(
            )
        )
        return quantomix2
    }

    @BeforeEach
    fun math(): Attack {
        return Attack(
            attackName = "Math",
            type = Typ.Normal,
            damage = 50,
            damageQuote = 100,
            specialAttack = false,
            effects = mutableListOf(
                Effects(
                    buff = false,
                    changeStats = Stats(
                        kp = 0,
                        attack = 10,
                        defense = 5,
                        specialAttack = 15,
                        specialDefense = 8,
                        speed = 20
                    ),
                    self = false
                )
            ),
            status = null
        )
    }

    @BeforeEach
    fun damageStrategy(): DamageStrategy {
        val damageFunction: DamageStrategy = StandardDamageStrategy()
        return damageFunction
    }

    @Test
    fun `Test Attack with damage and debuff`() {
        val quantomix1 = quantomix1()
        val quantomix2 = quantomix2()
        val quantomix1BattleStats = quantomix1.newBattleStats()
        val quantomix2BattleStats = quantomix2.newBattleStats()
        val battle: Battle = SimpleBattle()
        val solution = battle.simpleBattle(
            aktiveQuantomixBattleStats = quantomix1BattleStats,
            attack = math(),
            target = quantomix2BattleStats,
            attackStrategy = damageStrategy(),
        )
        assertThat(solution).isEqualTo(false)
        assertThat(quantomix2BattleStats.getStats().getKp()).isEqualTo(50)
        assertThat(quantomix2BattleStats.getStats().getAttack()).isEqualTo(quantomix2.getStats().getAttack() - 10)
        assertThat(quantomix2BattleStats.getStats().getSpecialAttack()).isEqualTo(quantomix2.getStats().getSpecialAttack() - 15)
        assertThat(quantomix2BattleStats.getStats().getDefense()).isEqualTo(quantomix2.getStats().getDefense() - 5)
        assertThat(quantomix2BattleStats.getStats().getSpecialDefense())
            .isEqualTo(quantomix2.getStats().getSpecialDefense() - 8)
        assertThat(quantomix2BattleStats.getStats().getSpeed()).isEqualTo(quantomix2.getStats().getSpeed() - 20)
    }
    @Test
    fun `Test Debuff without Attackdamage`() {

    }
}
/*
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
        Assertions.assertThat(quantomix1.battleStats.getStats().kp).isEqualTo(100)
        Assertions.assertThat(quantomix1.battleStats.getStats().getAttack()).isEqualTo(quantomix1.getStats().getAttack() - 10)
        Assertions.assertThat(quantomix1.battleStats.getStats().getSpecialAttack()).isEqualTo(quantomix1.getStats().getSpecialAttack() - 15)
        Assertions.assertThat(quantomix1.battleStats.getStats().getDefense()).isEqualTo(quantomix1.getStats().getDefense() - 5)
        Assertions.assertThat(quantomix1.battleStats.getStats().getSpecialDefense())
            .isEqualTo(quantomix1.getStats().getSpecialDefense() - 8)
        Assertions.assertThat(quantomix1.battleStats.getStats().getSpeed()).isEqualTo(quantomix1.getStats().getSpeed() - 20)
        Assertions.assertThat(quantomix2.battleStats.getStats().kp).isEqualTo(100)
        Assertions.assertThat(quantomix2.battleStats.getStats().getAttack()).isEqualTo(quantomix2.getStats().getAttack() - 10)
        Assertions.assertThat(quantomix2.battleStats.getStats().getSpecialAttack()).isEqualTo(quantomix2.getStats().getSpecialAttack() - 15)
        Assertions.assertThat(quantomix2.battleStats.getStats().getDefense()).isEqualTo(quantomix2.getStats().getDefense() - 5)
        Assertions.assertThat(quantomix2.battleStats.getStats().getSpecialDefense())
            .isEqualTo(quantomix2.getStats().getSpecialDefense() - 8)
        Assertions.assertThat(quantomix2.battleStats.getStats().getSpeed()).isEqualTo(quantomix2.getStats().getSpeed() - 20)
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
        Assertions.assertThat(quantomix1.battleStats.getStats().kp).isEqualTo(100)
        Assertions.assertThat(quantomix1.battleStats.getStats().getAttack()).isEqualTo(quantomix1.getStats().getAttack() + 10)
        Assertions.assertThat(quantomix1.battleStats.getStats().getSpecialAttack()).isEqualTo(quantomix1.getStats().getSpecialAttack() + 15)
        Assertions.assertThat(quantomix1.battleStats.getStats().getDefense()).isEqualTo(quantomix1.getStats().getDefense() + 5)
        Assertions.assertThat(quantomix1.battleStats.getStats().getSpecialDefense())
            .isEqualTo(quantomix1.getStats().getSpecialDefense() + 8)
        Assertions.assertThat(quantomix1.battleStats.getStats().getSpeed()).isEqualTo(quantomix1.getStats().getSpeed() + 20)
        Assertions.assertThat(quantomix2.battleStats.getStats().kp).isEqualTo(100)
        Assertions.assertThat(quantomix2.battleStats.getStats().getAttack()).isEqualTo(quantomix2.getStats().getAttack() + 10)
        Assertions.assertThat(quantomix2.battleStats.getStats().getSpecialAttack()).isEqualTo(quantomix2.getStats().getSpecialAttack() + 15)
        Assertions.assertThat(quantomix2.battleStats.getStats().getDefense()).isEqualTo(quantomix2.getStats().getDefense() + 5)
        Assertions.assertThat(quantomix2.battleStats.getStats().getSpecialDefense())
            .isEqualTo(quantomix2.getStats().getSpecialDefense() + 8)
        Assertions.assertThat(quantomix2.battleStats.getStats().getSpeed()).isEqualTo(quantomix2.getStats().getSpeed() + 20)
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
        Assertions.assertThat(quantomix1.battleStats.getStats().kp).isEqualTo(50)
        Assertions.assertThat(quantomix1.battleStats.getStats().getAttack()).isEqualTo(quantomix1.getStats().getAttack() + 10)
        Assertions.assertThat(quantomix1.battleStats.getStats().getSpecialAttack()).isEqualTo(quantomix1.getStats().getSpecialAttack() + 15)
        Assertions.assertThat(quantomix1.battleStats.getStats().getDefense()).isEqualTo(quantomix1.getStats().getDefense() + 5)
        Assertions.assertThat(quantomix1.battleStats.getStats().getSpecialDefense())
            .isEqualTo(quantomix1.getStats().getSpecialDefense() + 8)
        Assertions.assertThat(quantomix1.battleStats.getStats().getSpeed()).isEqualTo(quantomix1.getStats().getSpeed() + 20)
        Assertions.assertThat(quantomix2.battleStats.getStats().kp).isEqualTo(50)
        Assertions.assertThat(quantomix2.battleStats.getStats().getAttack()).isEqualTo(quantomix2.getStats().getAttack() + 10)
        Assertions.assertThat(quantomix2.battleStats.getStats().getSpecialAttack()).isEqualTo(quantomix2.getStats().getSpecialAttack() + 15)
        Assertions.assertThat(quantomix2.battleStats.getStats().getDefense()).isEqualTo(quantomix2.getStats().getDefense() + 5)
        Assertions.assertThat(quantomix2.battleStats.getStats().getSpecialDefense())
            .isEqualTo(quantomix2.getStats().getSpecialDefense() + 8)
        Assertions.assertThat(quantomix2.battleStats.getStats().getSpeed()).isEqualTo(quantomix2.getStats().getSpeed() + 20)
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
        Assertions.assertThat(quantomix1.battleStats.getStats().kp).isEqualTo(0)
        Assertions.assertThat(quantomix2.battleStats.getStats().kp).isEqualTo(100)
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
        attack2.status = Status(true)
        quantomix1.battleStats.nextAttack = attack1
        quantomix1.battleStats.target = quantomix2
        quantomix2.battleStats.nextAttack = attack2
        quantomix2.battleStats.target = quantomix1
        val battle = Battle(mutableListOf(quantomix1, quantomix2))
        battle.start()
        Assertions.assertThat(quantomix1.battleStats.getStats().kp).isEqualTo(75)
        Assertions.assertThat(quantomix1.battleStats.getStats().getAttack()).isEqualTo(quantomix1.getStats().getAttack() - 10)
        Assertions.assertThat(quantomix1.battleStats.getStats().getSpecialAttack()).isEqualTo(quantomix1.getStats().getSpecialAttack() - 20)
        Assertions.assertThat(quantomix1.battleStats.getStats().getDefense()).isEqualTo(quantomix1.getStats().getDefense() + 10)
        Assertions.assertThat(quantomix1.battleStats.getStats().getSpecialDefense())
            .isEqualTo(quantomix1.getStats().getSpecialDefense() + 20)
        Assertions.assertThat(quantomix1.battleStats.getStats().getSpeed()).isEqualTo(quantomix1.getStats().getSpeed() - 25)
        Assertions.assertThat(quantomix2.battleStats.getStats().kp).isEqualTo(75)
        Assertions.assertThat(quantomix2.battleStats.getStats().getAttack()).isEqualTo(quantomix2.getStats().getAttack() + 10)
        Assertions.assertThat(quantomix2.battleStats.getStats().getSpecialAttack()).isEqualTo(quantomix2.getStats().getSpecialAttack() + 20)
        Assertions.assertThat(quantomix2.battleStats.getStats().getDefense()).isEqualTo(quantomix2.getStats().getDefense() - 10)
        Assertions.assertThat(quantomix2.battleStats.getStats().getSpecialDefense())
            .isEqualTo(quantomix2.getStats().getSpecialDefense() + 8)
        Assertions.assertThat(quantomix2.battleStats.getStats().getSpeed()).isEqualTo(quantomix2.getStats().getSpeed() + 25)

    }
 */