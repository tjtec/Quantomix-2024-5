package hwr.oop.quantomix.fight

import hwr.oop.quantomix.fight.logic.Battle
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

class RoundWith2QuantomixTest: AnnotationSpec() {
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
            quantomixName = "Quantomix1", typ1 = Typ.Geist, typ2 = Typ.Feuer, stats = Stats(
                kp = 60, attack = 60, defense = 60, specialAttack = 60, specialDefense = 60, speed = 60
            ), attacks = testAttacks()
        )
        return testQuantomix1
    }

    @BeforeEach
    fun testQuantomix2(): Quantomix {
        val testQuantomix2 = Quantomix(
            quantomixName = "Quantomix2", typ1 = Typ.Eis, typ2 = Typ.Elektro, stats = Stats(
                kp = 50, attack = 50, defense = 50, specialAttack = 50, specialDefense = 50, speed = 50
            ), attacks = testAttacks()
        )
        return testQuantomix2
    }

    @BeforeEach
    fun testCoach1(): Coach {
        val testCoach1 = Coach(
            coachName = "Pepe", quantomixTeam = listOf(
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
            coachName = "Lilly", quantomixTeam = listOf(
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

    fun performRound(
        battle: Battle,
        coach1: Coach,
        coach2: Coach,
        attackIndexCoach1: Int,
        attackIndexCoach2: Int
    ) {
        val attacks = testAttacks()

        // Erhalte die aktiven Gegner als Ziel: Hier gehen wir davon aus, dass pro Trainer 2 Quantomix aktiv sein sollen.
        val activeTeamCoach2 = coach2.getActiveQuantomixes(numberOfQuantomixInRound = 2, battle.quantomixAndBattleStatsMap)
        val activeTeamCoach1 = coach1.getActiveQuantomixes(numberOfQuantomixInRound = 2, battle.quantomixAndBattleStatsMap)

        // Coach1 wählt beide seiner Angriffe:
        val movesCoach1 = listOf(
            Pair(attacks[attackIndexCoach1], activeTeamCoach2[0]),
            Pair(attacks[(attackIndexCoach1 + 1) % attacks.size], activeTeamCoach2[1])
        )
        battle.chooseAttacks(coach1, movesCoach1)

        // Danach wählt Coach2 beide seiner Angriffe:
        val movesCoach2 = listOf(
            Pair(attacks[attackIndexCoach2], activeTeamCoach1[0]),
            Pair(attacks[(attackIndexCoach2 + 1) % attacks.size], activeTeamCoach1[1])
        )
        battle.chooseAttacks(coach2, movesCoach2)
    }


    @Test
    fun `RoundsWith2Quantomix with two trainers are created successfully`() {
        val coach1 = testCoach1()
        val coach2 = testCoach2()

        // Voraussetzungen prüfen
        require(coach1.quantomixTeam.isNotEmpty()) { "Coach1 hat keine Quantomixe" }
        require(coach2.quantomixTeam.isNotEmpty()) { "Coach2 hat keine Quantomixe" }

        // Erstelle die Runde – es sollte keine Exception entstehen.
        val rounds = Battle(coach1, coach2, numberOfQuantomixInRound = 2)
        assertNotNull(rounds)

        // Attacken müssen vorhanden sein
        val attacks = testAttacks()
        require(attacks.isNotEmpty()) { "Keine Attacken verfügbar" }
    }

    @Test
    fun `choosing attack twice for same Quantomix throws exception`() {
        val coach1 = testCoach1()
        val coach2 = testCoach2()
        val rounds = Battle(coach1, coach2, numberOfQuantomixInRound = 2)
        val attacks = testAttacks()

        // Wähle einen aktiven Quantomix aus Coach1
        val activeTeamCoach1 = coach1.getActiveQuantomixes(2)
        rounds.chooseAttack(coach2, attacks[0], activeTeamCoach1[0])
        rounds.chooseAttack(coach2, attacks[0], activeTeamCoach1[0])
        val exception = assertThrows(IllegalStateException::class.java) {
            rounds.chooseAttack(coach2, attacks[1], activeTeamCoach1[1])
        }
        assertEquals("Attacking trainer has already chosen an attack", exception.message)
    }

    @Test
    fun `choosing a target from same team throws exception`() {
        val coach1 = testCoach1()
        val coach2 = testCoach2()
        val rounds = Battle(coach1, coach2, numberOfQuantomixInRound = 2)

        // Versuche, einem aktiven Quantomix ein Ziel aus dem eigenen Team zuzuweisen.
        val activeTeamCoach1 = coach1.getActiveQuantomixes(2)
        val exception = assertThrows(IllegalArgumentException::class.java) {
            rounds.chooseAttack(coach1, testAttacks()[0], activeTeamCoach1[0])
        }
        assertEquals("The chosen target does not belong to the other trainer.", exception.message)
    }

    @Test
    fun `simulate multiple rounds without exceptions`() {
        val coach1 = testCoach1()
        val coach2 = testCoach2()
        val rounds = Battle(coach1, coach2, numberOfQuantomixInRound = 2)
        val attacks = testAttacks()
        require(attacks.isNotEmpty()) { "Attackenliste darf nicht leer sein" }

        // Führe drei Runden durch
        for (round in 0 until 3) {
            // Wähle jeweils andere Indizes, sodass sich die ausgesuchten Attacken rotieren
            val attackIndexCoach1 = round % attacks.size
            val attackIndexCoach2 = (round + 1) % attacks.size

            performRound(rounds, coach1, coach2, attackIndexCoach1, attackIndexCoach2)

            // Nach einer Round sollten die aktiven Quantomixe weiterhin verfügbar sein (das heißt, sie wurden nicht "ausgeschaltet")
            assertNotNull(coach1.getActiveQuantomixes(2).first())
            assertNotNull(coach2.getActiveQuantomixes(2).first())
        }
    }
}

