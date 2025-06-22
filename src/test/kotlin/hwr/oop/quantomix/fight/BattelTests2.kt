package hwr.oop.quantomix.fight

import hwr.oop.quantomix.Exceptions.DeadQuantomixException
import hwr.oop.quantomix.fight.logic.DamageStrategy
import hwr.oop.quantomix.fight.logic.Round
import hwr.oop.quantomix.fight.logic.StandardDamageStrategy
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Effects
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.fight.objects.Status
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows

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
          heal = false,
          changeStats = Stats(
            kp = 0,
            attack = -4,
            defense = -5,
            specialAttack = -3,
            specialDefense = -2,
            speed = -1
          ),
          self = false
        )
      ),
      status = null
    )
  }

  @BeforeEach
  fun oop(): Attack {
    return Attack(
      attackName = "Objektorientierte Programmierung",
      type = Typ.Normal,
      damage = 50,
      damageQuote = 100,
      specialAttack = false,
      effects = mutableListOf(
        Effects(
          heal = false,
          changeStats = Stats(
            kp = 0,
            attack = 6,
            defense = 5,
            specialAttack = 3,
            specialDefense = 2,
            speed = 1
          ),
          self = false
        )
      ),
      status = null
    )
  }

  @BeforeEach
  fun bwl(): Attack {
    return Attack(
      attackName = "BWL",
      type = Typ.Normal,
      damage = 50,
      damageQuote = 100,
      specialAttack = false,
      effects = mutableListOf(
        Effects(
          heal = true,
          changeStats = Stats(
            kp = 0,
            attack = 4,
            defense = 6,
            specialAttack = 3,
            specialDefense = 2,
            speed = 1
          ),
          self = true
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
    val battle = Round()
    val solution = battle.startAttack(
      aktiveQuantomixBattleStats = quantomix1BattleStats,
      attack = math(),
      target = quantomix2BattleStats,
      attackStrategy = damageStrategy(),
    )
    assertThat(solution).isEqualTo(true)
    assertThat(quantomix2BattleStats.getStats().getKp()).isEqualTo(75)
    assertThat(quantomix2BattleStats.getStats().getAttack()).isEqualTo(
      (33)
    )
    assertThat(quantomix2BattleStats.getStats().getSpecialAttack()).isEqualTo(
      40
    )
    assertThat(quantomix2BattleStats.getStats().getDefense()).isEqualTo(
      28
    )
    assertThat(quantomix2BattleStats.getStats().getSpecialDefense())
      .isEqualTo(50)
    assertThat(
      quantomix2BattleStats.getStats().getSpeed()
    ).isEqualTo(67)
  }

  @Test
  fun `Test Debuff without Attackdamage`() {
    val attack1 = Attack(
      attackName = "No Damage and Debuff",
      type = Typ.Normal,
      damage = 0,
      damageQuote = 100,
      specialAttack = false,
      effects = mutableListOf(
        Effects(
          heal = false,
          changeStats = Stats(
            kp = 0,
            attack = -4,
            defense = -6,
            specialAttack = -3,
            specialDefense = -2,
            speed = -1
          ),
          self = false
        )
      ),
      status = null
    )
    val quantomix1 = quantomix1()
    val quantomix2 = quantomix2()
    val quantomix1BattleStats = quantomix1.newBattleStats()
    val quantomix2BattleStats = quantomix2.newBattleStats()
    val battle = Round()
    battle.startAttack(
      aktiveQuantomixBattleStats = quantomix1BattleStats,
      attack = attack1,
      target = quantomix2BattleStats,
      attackStrategy = damageStrategy()
    )
    assertThat(quantomix2BattleStats.getStats().getKp()).isEqualTo(100)
    assertThat(quantomix2BattleStats.getStats().getAttack())
      .isEqualTo(33)
    assertThat(quantomix2BattleStats.getStats().getSpecialAttack())
      .isEqualTo(40)
    assertThat(quantomix2BattleStats.getStats().getDefense())
      .isEqualTo(25)
    assertThat(quantomix2BattleStats.getStats().getSpecialDefense())
      .isEqualTo(50)
    assertThat(quantomix2BattleStats.getStats().getSpeed())
      .isEqualTo(67)
  }

  @Test
  fun `Test Buff of the attacker`() {
    val attack1 = bwl()
    val quantomix1 = quantomix1()
    val quantomix2 = quantomix2()
    val quantomix1BattleStats = quantomix1.newBattleStats()
    val quantomix2BattleStats = quantomix2.newBattleStats()
    val battle = Round()
    battle.startAttack(
      aktiveQuantomixBattleStats = quantomix1BattleStats,
      attack = attack1,
      target = quantomix2BattleStats,
      attackStrategy = damageStrategy()
    )

    assertThat(quantomix1BattleStats.getStats().getKp()).isEqualTo(100)
    assertThat(quantomix1BattleStats.getStats().getAttack()).isEqualTo(300)
    assertThat(quantomix1BattleStats.getStats().getSpecialAttack()).isEqualTo(
      250
    )
    assertThat(quantomix1BattleStats.getStats().getDefense()).isEqualTo(400)
    assertThat(quantomix1BattleStats.getStats().getSpecialDefense())
      .isEqualTo(200)
    assertThat(quantomix2BattleStats.getStats().getSpeed()).isEqualTo(100)
    assertThat(quantomix2BattleStats.getStats().getKp()).isEqualTo(75)
  }

  @Test
  fun `Test Attack and attacker is dead`() {
    val quantomix1 = quantomix1()
    val quantomix2 = quantomix2()
    val quantomix1BattleStats = quantomix1.newBattleStats()
    val quantomix2BattleStats = quantomix2.newBattleStats()

    val attackDeadly = Attack(
      attackName = "Direkter Treffer",
      type = Typ.Normal,
      damage = 100,
      damageQuote = 100,
      specialAttack = false,
      effects = mutableListOf(
        Effects(
          heal = false,
          changeStats = Stats(
            kp = 100,
            attack = 0,
            defense = 0,
            specialAttack = 0,
            specialDefense = 0,
            speed = 0
          ),
          self = false
        )
      ),
      status = null
    )
    val battle = Round()
    battle.startAttack(
      aktiveQuantomixBattleStats = quantomix1BattleStats,
      attack = attackDeadly,
      target = quantomix2BattleStats,
      attackStrategy = damageStrategy(),
    )
    assertThat(quantomix1BattleStats.getStats().getKp()).isEqualTo(100)
    assertThat(quantomix2BattleStats.getStats().getKp()).isEqualTo(0)
    val exception = assertThrows(DeadQuantomixException::class.java) {
      battle.startAttack(
        aktiveQuantomixBattleStats = quantomix2BattleStats,
        attack = attackDeadly,
        target = quantomix1BattleStats,
        attackStrategy = damageStrategy()
      )
    }
    assertEquals(
      "The Quantomix which would attack next is already dead!",
      exception.message
    )

  }

  @Test
  fun `Test with Attack with heal and self debuff and no damage`() {
    val quantomix1 = quantomix1()
    val quantomix2 = quantomix2()
    val quantomix1BattleStats = quantomix1.newBattleStats()
    val quantomix2BattleStats = quantomix2.newBattleStats()
    val attackComplex = Attack(
      attackName = "Komplexe Strategie",
      type = Typ.Normal,
      damage = 0,
      damageQuote = 100,
      specialAttack = false,
      effects = mutableListOf(
        Effects(
          heal = true,
          changeStats = Stats(
            kp = 25,
            attack = 0,
            defense = -6,
            specialAttack = 0,
            specialDefense = -5,
            speed = 0
          ),
          self = true
        ),
        Effects(
          heal = false,
          changeStats = Stats(
            kp = 0,
            attack = -5,
            defense = 0,
            specialAttack = -4,
            specialDefense = 0,
            speed = -5
          ),
          self = true
        )
      ),
      status = Status.NoDamage
    )

    val battle = Round()
    battle.startAttack(
      aktiveQuantomixBattleStats = quantomix1BattleStats,
      attack = attackComplex,
      target = quantomix2BattleStats,
      attackStrategy = damageStrategy(),
    )

    assertThat(quantomix1BattleStats.getStats().getKp()).isEqualTo(125)
    assertThat(quantomix1BattleStats.getStats().getAttack()).isEqualTo(28)
    assertThat(
      quantomix1BattleStats.getStats().getSpecialAttack()
    ).isEqualTo(33)
    assertThat(quantomix1BattleStats.getStats().getDefense()).isEqualTo(25)
    assertThat(quantomix1BattleStats.getStats().getSpecialDefense()).isEqualTo(
      28
    )
    assertThat(quantomix1BattleStats.getStats().getSpeed()).isEqualTo(28)
  }

  @Test
  fun `Test with Attack with self damage and self buff and no damage`() {
    val quantomix1 = quantomix1()
    val quantomix2 = quantomix2()
    val quantomix1BattleStats = quantomix1.newBattleStats()
    val quantomix2BattleStats = quantomix2.newBattleStats()

    val attackComplex = Attack(
      attackName = "Komplexe Strategie",
      type = Typ.Normal,
      damage = 0,
      damageQuote = 100,
      specialAttack = false,
      effects = mutableListOf(
        Effects(
          heal = false,
          changeStats = Stats(
            kp = 25,
            attack = 0,
            defense = 6,
            specialAttack = 0,
            specialDefense = 5,
            speed = 0
          ),
          self = true
        ),
        Effects(
          heal = false,
          changeStats = Stats(
            kp = 0,
            attack = 5,
            defense = 0,
            specialAttack = 4,
            specialDefense = 0,
            speed = 5
          ),
          self = true
        )
      ),
      status = Status.NoDamage
    )

    val battle = Round()
    battle.startAttack(
      aktiveQuantomixBattleStats = quantomix1BattleStats,
      attack = attackComplex,
      target = quantomix2BattleStats,
      attackStrategy = damageStrategy(),
    )

    assertThat(quantomix1BattleStats.getStats().getKp()).isEqualTo(75)
    assertThat(quantomix1BattleStats.getStats().getAttack()).isEqualTo(350)
    assertThat(quantomix1BattleStats.getStats().getSpecialAttack()).isEqualTo(
      300
    )
    assertThat(quantomix1BattleStats.getStats().getDefense()).isEqualTo(400)
    assertThat(quantomix1BattleStats.getStats().getSpecialDefense()).isEqualTo(
      350
    )
    assertThat(quantomix1BattleStats.getStats().getSpeed()).isEqualTo(350)
  }

  @Test
  fun `Test Attack with damage and buff`() {
    val quantomix1 = quantomix1()
    val quantomix2 = quantomix2()
    val quantomix1BattleStats = quantomix1.newBattleStats()
    val quantomix2BattleStats = quantomix2.newBattleStats()
    val battle = Round()
    val solution = battle.startAttack(
      aktiveQuantomixBattleStats = quantomix1BattleStats,
      attack = oop(),
      target = quantomix2BattleStats,
      attackStrategy = damageStrategy(),
    )
    assertThat(solution).isEqualTo(true)
    assertThat(quantomix2BattleStats.getStats().getKp()).isEqualTo(75)
    assertThat(quantomix2BattleStats.getStats().getAttack()).isEqualTo(
      (400)
    )
    assertThat(quantomix2BattleStats.getStats().getSpecialAttack()).isEqualTo(
      250
    )
    assertThat(quantomix2BattleStats.getStats().getDefense()).isEqualTo(
      350
    )
    assertThat(quantomix2BattleStats.getStats().getSpecialDefense())
      .isEqualTo(200)
    assertThat(
      quantomix2BattleStats.getStats().getSpeed()
    ).isEqualTo(150)
  }
}