package hwr.oop.quantomix.fight
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class BattelTests2:AnnotationSpec(){
/*

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
        attack2.status = Status(true)
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
 */

}