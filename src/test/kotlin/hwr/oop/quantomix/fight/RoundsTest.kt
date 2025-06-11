package hwr.oop.quantomix.fight

import hwr.oop.quantomix.fight.logic.Rounds
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows


class RoundsTest : AnnotationSpec() {
  @BeforeEach
  fun testAttacks(): List<Attack> {
    val fluch = Attack(
      attackName = "Fluch",
      type = Typ.Geist,
      damage = 30,
      damageQuote = 100,
      specialAttack = true,
      effects = mutableListOf()
    )
    val tackle = Attack(
      attackName = "Tackle",
      type = Typ.Normal,
      damage = 40,
      damageQuote = 100,
      specialAttack = false,
      effects = mutableListOf()
    )
    val eissturm = Attack(
      attackName = "Eissturm",
      type = Typ.Eis,
      damage = 30,
      damageQuote = 100,
      specialAttack = false,
      effects = mutableListOf()
    )
    val funkensprung = Attack(
      attackName = "funkensprung",
      type = Typ.Elektro,
      damage = 60,
      damageQuote = 100,
      specialAttack = true,
      effects = mutableListOf()
    )
    return listOf(fluch, tackle, eissturm, funkensprung)
  }

  @BeforeEach
  fun testQuantomix1(): Quantomix {
    val testQuantomix1 = Quantomix(
      quantomixName = "Quantomix1",
      typ1 = Typ.Geist,
      typ2 = Typ.Feuer,
      stats = Stats(
        kp = 60,
        attack = 60,
        defense = 60,
        specialAttack = 60,
        specialDefense = 60,
        speed = 60
      ),
      attacks = testAttacks()
    )
    return testQuantomix1
  }

  @BeforeEach
  fun testQuantomix2(): Quantomix {
    val testQuantomix2 = Quantomix(
      quantomixName = "Quantomix2",
      typ1 = Typ.Eis,
      typ2 = Typ.Elektro,
      stats = Stats(
        kp = 50,
        attack = 50,
        defense = 50,
        specialAttack = 50,
        specialDefense = 50,
        speed = 50
      ),
      attacks = testAttacks()
    )
    return testQuantomix2
  }

  @BeforeEach
  fun testCoach1(): Coach {
    val testCoach1 = Coach(
      coachName = "Pepe",
      quantomixTeam = listOf(
        testQuantomix1(),
        testQuantomix2(),
        testQuantomix1(),
        testQuantomix2(),
        testQuantomix1(),
        testQuantomix2()
      )
    )
    return testCoach1
  }

  @BeforeEach
  fun testCoach2(): Coach {
    val testCoach2 = Coach(
      coachName = "Lilly",
      quantomixTeam = listOf(
        testQuantomix2(),
        testQuantomix1(),
        testQuantomix2(),
        testQuantomix1(),
        testQuantomix2(),
        testQuantomix2()
      )
    )
    return testCoach2
  }

  private fun performRound(
    rounds: Rounds,
    coach1: Coach,
    coach2: Coach,
    attackIndexCoach1: Int,
    attackIndexCoach2: Int,
  ) {
    val attacks = testAttacks()
    rounds.chooseAttack(coach1, attacks[attackIndexCoach1])
    rounds.chooseAttack(coach2, attacks[attackIndexCoach2])
  }

  @Test
  fun `Rounds with two trainers`() {
    // Erstelle die Coaches
    val coach1 = testCoach1()
    val coach2 = testCoach2()
    // Überprüfe die Voraussetzungen
    require(coach1.quantomixTeam.isNotEmpty()) { "Coach1 hat keine Quantomixe" }
    require(coach2.quantomixTeam.isNotEmpty()) { "Coach2 hat keine Quantomixe" }

    // Erstelle die Runde
    val rounds=Rounds(coach1, coach2)
    // Hole die Attacken und prüfe sie
    val attacks = testAttacks()
    require(attacks.isNotEmpty()) { "Keine Attacken verfügbar" }

    assertDoesNotThrow {
      rounds.choseAttack(coach1, attacks[0])
      rounds.choseAttack(coach2, attacks[1])
    }

  }

  @Test
  fun `choosing attack twice for same trainer throws exception`() {
    val coach1 = testCoach1()
    val coach2 = testCoach2()
    val rounds = Rounds(coach1, coach2)
    val attacks = testAttacks()

    rounds.chooseAttack(coach1, attacks[0])
    val exception = assertThrows(IllegalArgumentException::class.java) {
      rounds.chooseAttack(coach1, attacks[1])
    }
    assertEquals(
      "Attacking trainer has already chosen an attack",
      exception.message
    )
  }

  @Test
  fun `choosing an attack not possessed by Quantomix throws exception`() {
    val coach1 = testCoach1()
    val coach2 = testCoach2()
    val rounds = Rounds(coach1, coach2)

    // Erzeuge einen Angriff, den der aktive Quantomix nicht besitzt.
    val invalidAttack = Attack(
      attackName = "Unsichtbarer Schlag",
      type = Typ.Normal,
      damage = 50,
      damageQuote = 100,
      specialAttack = false,
      effects = mutableListOf()
    )
    val exception = assertThrows(IllegalArgumentException::class.java) {
      rounds.chooseAttack(coach1, invalidAttack)
    }
    assertEquals(
      "Attacking Quantomix does not have the attack",
      exception.message
    )
  }

  @Test
  fun `simulate multiple rounds without exceptions`() {
    val coach1 = testCoach1()
    val coach2 = testCoach2()
    val rounds = Rounds(coach1, coach2)
    val attacks = testAttacks() // Einmalig aufrufen

    require(attacks.isNotEmpty()) { "Attackenliste darf nicht leer sein" }
    for (round in 0 until 3) {
      val attackIndexCoach1 = round % attacks.size
      val attackIndexCoach2 = (round + 1) % attacks.size

      performRound(rounds, coach1, coach2, attackIndexCoach1, attackIndexCoach2)

      assertNotNull(coach1.getFirstQuantomix())
      assertNotNull(coach2.getFirstQuantomix())
    }
  }
}