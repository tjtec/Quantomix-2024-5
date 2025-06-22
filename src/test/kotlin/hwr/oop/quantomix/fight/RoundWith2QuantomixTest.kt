package hwr.oop.quantomix.fight
/*
import hwr.oop.quantomix.fight.logic.RoundsWith2Quantomix
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec.BeforeEach
import io.kotest.core.spec.style.AnnotationSpec.Test
import io.kotest.core.spec.style.AnnotationSpec
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

class RoundWith2QuantomixTest {
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

    private fun performRound(
        rounds: RoundsWith2Quantomix, coach1: Coach, coach2: Coach, attackingIndexCoach1: Int, attackingIndexCoach2: Int
    ) {
        val attacks = testAttacks()
        val activeTeamCoach1 = coach1.getActiveQuantomix(numberOfQuantomixInRound = 2)
        val activeTeamCoach2 = coach2.getActiveQuantomix(numberOfQuantomixInRound = 2)

        rounds.choseAttack(activeTeamCoach1[0], attacks[attackingIndexCoach1])
        rounds.choseAttack(activeTeamCoach1[1], attacks[(attackingIndexCoach1 + 1) % attacks.size])
        rounds.choseAttack(activeTeamCoach2[0], attacks[attackingIndexCoach2])
        rounds.choseAttack(activeTeamCoach2[1], attacks[(attackingIndexCoach2 + 1) % attacks.size])

        rounds.chooseTarget(activeTeamCoach1[0], activeTeamCoach2[0])
        rounds.chooseTarget(activeTeamCoach1[1], activeTeamCoach2[1])
        rounds.chooseTarget(activeTeamCoach2[0], activeTeamCoach1[0])
        rounds.chooseTarget(activeTeamCoach2[1], activeTeamCoach1[1])
    }

    @Test
    fun `RoundsWith2Quantomix with two trainers are created successfully`() {
        val coach1 = testCoach1()
        val coach2 = testCoach2()

        // Voraussetzungen prüfen
        require(coach1.quantomixTeam.isNotEmpty()) { "Coach1 hat keine Quantomixe" }
        require(coach2.quantomixTeam.isNotEmpty()) { "Coach2 hat keine Quantomixe" }

        // Erstelle die Runde – es sollte keine Exception entstehen.
        val rounds = RoundsWith2Quantomix(coach1, coach2)
        assertNotNull(rounds)

        // Attacken müssen vorhanden sein
        val attacks = testAttacks()
        require(attacks.isNotEmpty()) { "Keine Attacken verfügbar" }
    }

    @Test
    fun `choosing attack twice for same Quantomix throws exception`() {
        val coach1 = testCoach1()
        val coach2 = testCoach2()
        val rounds = RoundsWith2Quantomix(coach1, coach2)
        val attacks = testAttacks()

        // Wähle einen aktiven Quantomix aus Coach1
        val activeTeamCoach1 = coach1.getActiveQuantomix(2)
        rounds.choseAttack(activeTeamCoach1[0], attacks[0])
        val exception = assertThrows(IllegalArgumentException::class.java) {
            rounds.choseAttack(activeTeamCoach1[0], attacks[1])
        }
        assertEquals("Attacking trainer has already chosen an attack", exception.message)
    }

    @Test
    fun `choosing target twice for same Quantomix throws exception`() {
        val coach1 = testCoach1()
        val coach2 = testCoach2()
        val rounds = RoundsWith2Quantomix(coach1, coach2)

        // Wähle zunächst ein Ziel für einen aktiven Quantomix
        val activeTeamCoach1 = coach1.getActiveQuantomix(2)
        val activeTeamCoach2 = coach2.getActiveQuantomix(2)
        rounds.chooseTarget(activeTeamCoach1[0], activeTeamCoach2[0])
        val exception = assertThrows(IllegalArgumentException::class.java) {
            rounds.chooseTarget(activeTeamCoach1[0], activeTeamCoach2[1])
        }
        assertEquals("Für diesen Quantomix wurde bereits ein Ziel ausgewählt.", exception.message)
    }

    @Test
    fun `choosing a target from same team throws exception`() {
        val coach1 = testCoach1()
        val coach2 = testCoach2()
        val rounds = RoundsWith2Quantomix(coach1, coach2)

        // Versuche, einem aktiven Quantomix ein Ziel aus dem eigenen Team zuzuweisen.
        val activeTeamCoach1 = coach1.getActiveQuantomix(2)
        val exception = assertThrows(IllegalArgumentException::class.java) {
            rounds.chooseTarget(activeTeamCoach1[0], activeTeamCoach1[1])
        }
        assertEquals("Das gewählte Ziel gehört nicht zur gegnerischen Mannschaft.", exception.message)
    }

    @Test
    fun `simulate multiple rounds without exceptions`() {
        val coach1 = testCoach1()
        val coach2 = testCoach2()
        val rounds = RoundsWith2Quantomix(coach1, coach2)
        val attacks = testAttacks()
        require(attacks.isNotEmpty()) { "Attackenliste darf nicht leer sein" }

        // Führe drei Runden durch
        for (round in 0 until 3) {
            // Wähle jeweils andere Indizes, sodass sich die ausgesuchten Attacken rotieren
            val attackIndexCoach1 = round % attacks.size
            val attackIndexCoach2 = (round + 1) % attacks.size

            performRound(rounds, coach1, coach2, attackIndexCoach1, attackIndexCoach2)

            // Nach einer Round sollten die aktiven Quantomixe weiterhin verfügbar sein (das heißt, sie wurden nicht "ausgeschaltet")
            assertNotNull(coach1.getActiveQuantomix(2).first())
            assertNotNull(coach2.getActiveQuantomix(2).first())
        }
    }
}

 */
