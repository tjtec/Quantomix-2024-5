package hwr.oop.quantomix.fight

import hwr.oop.quantomix.fight.logic.Battle
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.junit.jupiter.api.Assertions.*


class BattleTest : AnnotationSpec() {
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

  fun performBattle(
    rounds: Battle,
    coach1: Coach,
    coach2: Coach,
    attackIndexCoach1: Int,
    attackIndexCoach2: Int,
    targetIndexCoach1: Int = 0,
    targetIndexCoach2: Int = 0,
  ) {
    val attacks = testAttacks()
    val enemyTargetsForCoach1 = coach2.getActiveQuantomixes(
      1,
      battleStatsMap = rounds.quantomixAndBattleStatsMap
    )
    val targetForCoach1 =
      if (targetIndexCoach1 in enemyTargetsForCoach1.indices)
        enemyTargetsForCoach1[targetIndexCoach1]
      else
        enemyTargetsForCoach1.first()

    rounds.chooseAttack(coach1, attacks[attackIndexCoach1], targetForCoach1)

    // Analog für coach2:
    val enemyTargetsForCoach2 = coach1.getActiveQuantomixes(
      1,
      battleStatsMap = rounds.quantomixAndBattleStatsMap
    )
    val targetForCoach2 =
      if (targetIndexCoach2 in enemyTargetsForCoach2.indices)
        enemyTargetsForCoach2[targetIndexCoach2]
      else
        enemyTargetsForCoach2.first()

    rounds.chooseAttack(coach2, attacks[attackIndexCoach2], targetForCoach2)
  }

  @Test
  fun `Battle with two trainers`() {
    // Erstelle die Coaches
    val coach1 = testCoach1()
    val coach2 = testCoach2()

    // Überprüfe die Voraussetzungen
    require(coach1.quantomixTeam.isNotEmpty()) { "Coach1 hat keine Quantomixe" }
    require(coach2.quantomixTeam.isNotEmpty()) { "Coach2 hat keine Quantomixe" }

    // Erstelle die Runde
    val battle = Battle(trainer1 = coach1, trainer2 = coach2)

    // Hole die Attacken und prüfe sie
    val attacks = testAttacks()
    require(attacks.isNotEmpty()) { "Keine Attacken verfügbar" }
    assertDoesNotThrow {
      battle.chooseAttack(coach1, attacks[0], coach2.quantomixTeam[0])
      battle.chooseAttack(coach2, attacks[1], coach1.quantomixTeam[0])
    }
  }

  @Test
  fun `choosing attack twice for same trainer throws exception`() {
    val coach1 = testCoach1()
    val coach2 = testCoach2()
    val rounds = Battle(coach1, coach2)
    val attacks = testAttacks()

    rounds.chooseAttack(
      attackingTrainer = coach1,
      attack = attacks[0],
      target = coach2.quantomixTeam[0]
    )
    val exception =
      assertThrows(IllegalStateException::class.java) {
        rounds.chooseAttack(
          attackingTrainer = coach1,
          attack = attacks[1],
          target = coach2.quantomixTeam[0]
        )
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
    val rounds = Battle(coach1, coach2)

    // Erzeuge einen Angriff, den der aktive Quantomix nicht besitzt.
    val invalidAttack = Attack(
      attackName = "Unsichtbarer Schlag",
      type = Typ.Normal,
      damage = 50,
      damageQuote = 100,
      specialAttack = false,
      effects = mutableListOf()
    )
    val exception =
      assertThrows(IllegalArgumentException::class.java) {
        rounds.chooseAttack(
          attackingTrainer = coach1,
          attack = invalidAttack,
          target = coach2.quantomixTeam[0]
        )
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
    val battle = Battle(coach1, coach2)
    val attacks = testAttacks()
    require(attacks.isNotEmpty()) { "Attackenliste darf nicht leer sein" }

    for (round in 0 until 3) {
      val attackIndexCoach1 = round % attacks.size
      val attackIndexCoach2 = (round + 1) % attacks.size

      assertDoesNotThrow {
        performBattle(
          rounds = battle,
          coach1 = coach1,
          coach2 = coach2,
          attackIndexCoach1 = attackIndexCoach1,
          attackIndexCoach2 = attackIndexCoach2
        )
      }
    }
  }
}